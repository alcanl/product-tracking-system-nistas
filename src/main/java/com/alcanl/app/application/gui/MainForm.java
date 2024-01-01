package com.alcanl.app.application.gui;

import static com.alcanl.app.global.Resources.*;
import static com.google.common.io.Resources.getResource;

import com.alcanl.app.global.Resources;
import com.alcanl.app.global.SearchType;
import com.alcanl.app.repository.entity.Material;
import com.alcanl.app.service.ApplicationService;
import com.alcanl.app.service.ServiceException;
import javax.swing.*;
import javax.swing.plaf.synth.SynthTableUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.RoundingMode;
import java.util.List;


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
    private DefaultTableModel defaultTableModel;
    private ApplicationService applicationService;

    public MainForm()
    {
        Resources.setLayout(NIMBUS_THEME);
        add(jPanelMain);
        setSize(1400, 800);
        setTitle(COMPANY_NAME);
        setIconImage(Toolkit.getDefaultToolkit().createImage(getResource(DEFAULT_ICON)));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1024, 768));
        initializeLogo(labelLogo);

        try {
            applicationService = new ApplicationService();
        } catch (ServiceException ignore) {}

        setVisible(true);
        initializeTable();
        initializeButtons();
    }
    private void initializeTable()
    {
        initializeTableModel();
        tableProducts.setUI(new SynthTableUI());
        tableProducts.getTableHeader().setReorderingAllowed(false);
        tableProducts.getTableHeader().setResizingAllowed(false);
        tableProducts.getTableHeader().setUpdateTableInRealTime(false);
        tableProducts.setCellSelectionEnabled(false);

        fillTable(applicationService.getDataFromDB());
    }
    private void initializeTableModel()
    {
        defaultTableModel = new DefaultTableModel();
        Object[] tableHeaders = {TABLE_COLUMN_HEADER_NAME, TABLE_COLUMN_HEADER_LENGTH, TABLE_COLUMN_HEADER_RADIUS, TABLE_COLUMN_HEADER_UNIT_PRICE};
        defaultTableModel.setColumnIdentifiers(tableHeaders);
    }
    private void fillTableCallback(Material material)
    {
        var materialLength = material.getLength().orElse(NO_VALUE);
        var materialRadius = material.getRadius().orElse(0.0);
        Object[] data = {material.getName(), materialLength,
                materialRadius,
                material.calculateUnitSalePrice().setScale(2, RoundingMode.CEILING)};

        defaultTableModel.addRow(data);
        tableProducts.setModel(defaultTableModel);
    }
    private void fillTable(List<Material> list)
    {
        if (list.isEmpty()) {
            showEmptyListWarningMessageDialog();
            return;
        }

        list.forEach(this::fillTableCallback);
    }
    private void buttonGetAllProductClickedCallback()
    {
        initializeTableModel();
        fillTable(applicationService.getDataFromDB());
    }
    private void initializeButtons()
    {
        buttonGetAllData.addActionListener(e -> buttonGetAllProductClickedCallback());
        buttonSearchByLength.addActionListener(e -> buttonSearchByLengthClickedCallback());
        buttonSearchByName.addActionListener(e -> buttonSearchByNameClickedCallback());
        buttonSearchByRadius.addActionListener(e -> buttonSearchByRadiusClickedCallback());
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

        try {
            var list = switch (searchType) {
                case NAME -> applicationService.findByName(searchText);
                case LENGTH -> applicationService.findByLength(searchText);
                case RADIUS -> applicationService.findByRadius(Double.parseDouble(searchText));
            };

            initializeTableModel();
            fillTable(list);

        } catch (NumberFormatException ex)
        {
            showUnsupportedFormatWarningMessageDialog();
        }
    }
}
