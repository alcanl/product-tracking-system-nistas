package com.alcanl.app.application.gui;

import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.entity.Material;
import com.alcanl.app.service.ApplicationService;
import com.alcanl.app.service.ServiceException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.plaf.multi.MultiTableUI;
import javax.swing.plaf.synth.SynthTableUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.RoundingMode;

import static com.alcanl.app.global.Resources.*;

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
    private DefaultTableModel defaultTableModel;
    private ApplicationService applicationService;

    public MainForm()
    {
        add(jPanelMain);
        setSize(1400, 1000);
        setTitle(COMPANY_NAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1024, 768));

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            applicationService = new ApplicationService();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {

        }
        catch (ServiceException ex)
        {

        }
        setVisible(true);
        setTableModel();
    }
    public void setTableModel()
    {
        defaultTableModel = new DefaultTableModel();
        Object[] tableHeaders = {TABLE_COLUMN_HEADER_NAME, TABLE_COLUMN_HEADER_LENGTH, TABLE_COLUMN_HEADER_RADIUS, TABLE_COLUMN_HEADER_UNIT_PRICE};
        defaultTableModel.setColumnIdentifiers(tableHeaders);
        tableProducts.setUI(new SynthTableUI());
        tableProducts.setModel(defaultTableModel);
        tableProducts.getTableHeader().setReorderingAllowed(false);
        tableProducts.getTableHeader().setResizingAllowed(false);
        tableProducts.getTableHeader().setUpdateTableInRealTime(true);
        fillTable();
    }
    private void fillTableCallback(Material material)
    {
        var materialLength = material.getLength().orElse(NO_VALUE);
        var materialRadius = material.getRadius().orElse(0.0);
        Object[] data = {material.getName(), materialLength,
                materialRadius, material.calculateUnitSalePrice().setScale(2, RoundingMode.CEILING)};
        defaultTableModel.addRow(data);
    }
    private void fillTable()
    {
        applicationService.getDataFromDB().forEach(this::fillTableCallback);
    }
    private void tableOnItemSelectedListener()
    {
        tableProducts.setUI(new BasicTableUI());
    }

}
