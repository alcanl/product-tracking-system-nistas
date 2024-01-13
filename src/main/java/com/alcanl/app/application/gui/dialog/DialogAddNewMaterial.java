package com.alcanl.app.application.gui.dialog;

import com.alcanl.app.application.gui.MainForm;
import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.entity.Material;
import com.alcanl.app.service.ApplicationService;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;

@SuppressWarnings("ALL")
public class DialogAddNewMaterial extends JDialog {
    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JPanel panelSaveProcess;
    private JPanel panelDialog;
    private JTextField textFieldName;
    private JTextField textFieldLength;
    private JTextField textFieldRadius;
    private JTextField textFieldUnitPrice;
    private JLabel labelName;
    private JLabel labelLength;
    private JLabel labelRadius;
    private JLabel labelUnitPrice;
    private JPanel mainPanel;
    private final ApplicationService m_ApplicationService;
    public DialogAddNewMaterial(ApplicationService applicationService)
    {
        m_ApplicationService = applicationService;
        setSize(390, 290);
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getRootPane().setDefaultButton(buttonSave);

        buttonSave.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());
        dispose();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });


        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void onOK()
    {
        var material = getNewMaterialProps();

        if (material == null)
            return;

        m_ApplicationService.saveNewData(material);
        MainForm.IS_LIST_CHANGE = true;
        dispose();
    }

    private void onCancel()
    {
        dispose();
    }
    private String getMaterialName()
    {
        var test = textFieldName.getText();

        if (test.equals("")) {
            Resources.showEmptyNameTextErrorMessage();
            return null;
        }
        return textFieldName.getText();
    }
    private String getMaterialLength()
    {
        return textFieldLength.getText();
    }
    private double getMaterialRadius()
    {
        return textFieldRadius.getText().equals("") ? 0.0 : Double.parseDouble(textFieldRadius.getText());
    }
    private BigDecimal getMaterialUnitPrice()
    {
        if (textFieldUnitPrice.getText().equals("")) {
            Resources.showEmptyUnitPriceTextErrorMessage();
            return null;
        }

        return new BigDecimal(textFieldUnitPrice.getText());
    }
    public Material getNewMaterialProps()
    {
        try {
            var name = getMaterialName(); var length = getMaterialLength();
            var radius = getMaterialRadius(); var unitPrice = getMaterialUnitPrice();

            if (name == null || unitPrice == null)
                return null;

            return new Material(name, radius, length, unitPrice);
        }
        catch (NumberFormatException ignore)
        {
            Resources.showUnsupportedFormatWarningMessageDialog();
            return null;
        }
    }

}
