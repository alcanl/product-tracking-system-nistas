package com.alcanl.app.application.gui;

import static com.alcanl.app.global.Resources.*;
import static com.google.common.io.Resources.getResource;

import com.alcanl.app.application.gui.dialog.DialogAddNewMaterial;
import com.alcanl.app.application.gui.popup.TableItemRightClickPopUpMenu;
import com.alcanl.app.global.Resources;
import com.alcanl.app.global.SearchType;
import com.alcanl.app.repository.entity.Material;
import com.alcanl.app.service.ApplicationService;
import com.alcanl.app.service.ServiceException;
import javax.swing.*;
import javax.swing.plaf.synth.SynthTableUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainForm extends JFrame {
    private JPanel jPanelMain;
    private JButton buttonSearchByName;
    private JButton buttonSearchByLength;
    private JButton buttonSearchByRadius;
    private JTable tableProducts;
    private JTextField textFieldSearchByName;
    private JTextField textFieldSearchByLength;
    private JTextField textFieldSearchByRadius;
    private JPanel panelNameSeek;
    private JPanel panelLengthSeek;
    private JPanel panelRadiusSeek;
    private JPanel panelSeekBar;
    private JPanel panelBottomBar;
    private JPanel panelTable;
    private JPanel panelLogo;
    private JScrollPane paneTable;
    private JButton buttonGetAllData;
    private JList listSaleBasket;
    private JLabel labelLogo;
    private JButton button1;
    private JScrollPane scrollPaneSaleBasket;
    private JPanel panelTotal;
    private JPanel panelBasket;
    private JLabel labelTotalCount;
    private JButton buttonAddNewMaterial;
    private JButton button3;
    private JButton button4;
    private DefaultTableModel m_defaultTableModel;
    private ApplicationService m_applicationService;
    public static volatile boolean IS_LIST_CHANGE = false;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public MainForm()
    {
        try {
            m_applicationService = new ApplicationService();
        } catch (ServiceException ex) {
            showUnknownErrorMessageDialog(ex.getMessage());
        }

        initializeFrame();
        initializeLogo(labelLogo);
        initializeTable();
        initializeButtons();
        initializeTextFields();
        setVisible(true);
        startListEventListener();
    }
    private void initializeTable()
    {
        initializeTableModel();
        tableProducts.setUI(new SynthTableUI());
        tableProducts.getTableHeader().setReorderingAllowed(false);
        tableProducts.getTableHeader().setResizingAllowed(false);
        tableProducts.getTableHeader().setUpdateTableInRealTime(true);
        tableProducts.setComponentPopupMenu(new TableItemRightClickPopUpMenu(m_applicationService));
        fillTable(m_applicationService.getDataFromDB());
        tableProducts.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int currentRow = tableProducts.rowAtPoint(point);
                tableProducts.setRowSelectionInterval(currentRow, currentRow);

                if (SwingUtilities.isRightMouseButton(e))
                {
                    executorService.execute(()-> {
                        var materialName = (String) tableProducts.getValueAt(tableProducts.getSelectedRow(), 0);
                        var materialLength = tableProducts.getValueAt(tableProducts.getSelectedRow(), 1)
                                .equals(NO_VALUE) ? "" : (String) tableProducts.getValueAt(tableProducts.getSelectedRow(), 1);
                        var materialRadius = (double) tableProducts.getValueAt(tableProducts.getSelectedRow(), 2);
                        var materialPrice = (BigDecimal) tableProducts.getValueAt(tableProducts.getSelectedRow(), 3);

                        TableItemRightClickPopUpMenu.m_selectedMaterial = new Material(materialName, materialRadius, materialLength, materialPrice);
                    });
                }
            }
        });
    }
    private void initializeFrame()
    {
        Resources.setLayout(NIMBUS_THEME);
        add(jPanelMain);
        setSize(1400, 800);
        setTitle(COMPANY_NAME);
        setIconImage(Toolkit.getDefaultToolkit().createImage(getResource(DEFAULT_ICON)));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1024, 768));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e)
            {
                super.windowClosed(e);
                executorService.shutdown();
                ApplicationService.ms_threadPool.shutdown();
            }
        });
    }
    private void initializeTableModel()
    {
        m_defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        Object[] tableHeaders = {TABLE_COLUMN_HEADER_NAME, TABLE_COLUMN_HEADER_LENGTH, TABLE_COLUMN_HEADER_RADIUS, TABLE_COLUMN_HEADER_UNIT_PRICE};
        m_defaultTableModel.setColumnIdentifiers(tableHeaders);
    }
    private void fillTableCallback(Material material)
    {
        var materialLength = material.getLength().orElse(NO_VALUE);
        var materialRadius = material.getRadius().orElse(0.0);
        Object[] data = {material.getName(), materialLength,
                materialRadius,
                material.calculateUnitSalePrice().setScale(2, RoundingMode.CEILING)};

        m_defaultTableModel.addRow(data);
        tableProducts.setModel(m_defaultTableModel);
    }
    private void fillTable(List<Material> list)
    {
        if (list.isEmpty()) {
            showEmptyListWarningMessageDialog();
            return;
        }

        list.forEach(this::fillTableCallback);
        labelTotalCount.setText(String.format(TOTAL_TABLE_COUNT, list.size()));
    }
    private void buttonGetAllProductClickedCallback()
    {
        initializeTableModel();
        fillTable(m_applicationService.getDataFromDB());
    }
    private void initializeButtons()
    {
        buttonGetAllData.addActionListener(e -> buttonGetAllProductClickedCallback());
        buttonSearchByLength.addActionListener(e -> buttonSearchByLengthClickedCallback());
        buttonSearchByName.addActionListener(e -> buttonSearchByNameClickedCallback());
        buttonSearchByRadius.addActionListener(e -> buttonSearchByRadiusClickedCallback());
        buttonAddNewMaterial.addActionListener(e -> buttonAddNewMaterialClickedCallBack());
        setBottomBarButtonTheme(panelBottomBar);
        setButtonCursors(jPanelMain);

    }
    private void initializeTextFields()
    {
        setSeekBarTextFieldToButtonListener(textFieldSearchByRadius, buttonSearchByRadius);
        setSeekBarTextFieldToButtonListener(textFieldSearchByLength, buttonSearchByLength);
        setSeekBarTextFieldToButtonListener(textFieldSearchByName, buttonSearchByName);
    }
    private void setSeekBarTextFieldToButtonListener(JTextField textField, JButton button)
    {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    button.doClick();
            }
        });
    }
    private void buttonSearchByNameClickedCallback()
    {
        doCommonButtonWorks(textFieldSearchByName, SearchType.NAME);
    }
    private void buttonSearchByLengthClickedCallback()
    {
        doCommonButtonWorks(textFieldSearchByLength, SearchType.LENGTH);
    }
    private void buttonSearchByRadiusClickedCallback()
    {
        doCommonButtonWorks(textFieldSearchByRadius, SearchType.RADIUS);
    }
    private void doCommonButtonWorks(JTextField textField, SearchType searchType)
    {
        var searchText = textField.getText().trim();
        textField.setText("");

        try {
            var list = switch (searchType) {
                case NAME -> m_applicationService.findByName(searchText);
                case LENGTH -> m_applicationService.findByLength(searchText);
                case RADIUS -> m_applicationService.findByRadius(Double.parseDouble(searchText));
            };

            initializeTableModel();
            fillTable(list);

        } catch (NumberFormatException ex)
        {
            showUnsupportedFormatWarningMessageDialog();
        }
    }
    private void setButtonCursors(JPanel panel)
    {
        for (Component component : panel.getComponents()) {
            if (component instanceof JPanel panelChild)
                setButtonCursors(panelChild);
            else if (component instanceof JButton button)
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }
    private void setBottomBarButtonTheme(JPanel panel)
    {
        Arrays.stream(panel.getComponents()).filter(c -> c instanceof JButton)
                .map(c -> (JButton)c).forEach(this::setBottomBarButtonThemeCallback);

    }
    private void setBottomBarButtonThemeCallback(JButton button)
    {
        button.setBackground(Color.lightGray);
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.darkGray);
                button.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.lightGray);
                button.setForeground(Color.black);
            }
        });
    }
    private void buttonAddNewMaterialClickedCallBack()
    {
        var addNewMaterialDialog = new DialogAddNewMaterial(m_applicationService);
        addNewMaterialDialog.setLocationRelativeTo(null);
        addNewMaterialDialog.setVisible(true);
    }
    private void startListEventListener()
    {
        var thread = new Thread(() -> {
            while (true) {
                if (IS_LIST_CHANGE) {
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ignore) {

                    }
                    buttonGetAllProductClickedCallback();
                    IS_LIST_CHANGE = false;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
