package com.alcanl.app.application.gui.dialog;

import com.alcanl.app.service.ApplicationService;
import javax.swing.*;
import java.awt.event.*;

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
    private JCheckBox checkBoxIsPipeType;
    private final ApplicationService m_applicationService;
    public DialogAddNewMaterial(ApplicationService applicationService)
    {
        m_applicationService = applicationService;
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
        var material = DialogHelper.getNewMaterialProps(textFieldName, textFieldLength,
                    textFieldRadius, textFieldUnitPrice);

        if (material == null)
            return;


        if (checkBoxIsPipeType.isSelected())
            material.setIsPipeType(true);
        else
            material.setIsPipeType(false);


        DialogHelper.saveNewData(m_applicationService, material);
        dispose();
    }

    private void onCancel()
    {
        dispose();
    }
}
