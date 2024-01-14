package com.alcanl.app.application.gui.dialog;

import com.alcanl.app.application.gui.popup.TableItemRightClickPopUpMenu;
import com.alcanl.app.service.ApplicationService;
import javax.swing.*;
import java.awt.event.*;
@SuppressWarnings("ALL")
public class DialogUpdateMaterial extends JDialog {
    private JPanel contentPane;
    private JButton buttonSave;
    private JButton buttonCancel;
    private JPanel panelMain;
    private JPanel panelProps;
    private JPanel panelBottom;
    private JPanel panelButtons;
    private JPanel panelLabels;
    private JPanel panelTextFields;
    private JTextField textFieldUnitSalePrice;
    private JTextField textFieldUnitPrice;
    private JTextField textFieldRadius;
    private JTextField textFieldLength;
    private JTextField textFieldName;
    private JLabel labelUnitSalePrice;
    private JLabel labelName;
    private JLabel labelLength;
    private JLabel labelRadius;
    private JLabel labelUnitPrice;
    private final ApplicationService m_applicationService;

    public DialogUpdateMaterial(ApplicationService applicationService)
    {
        m_applicationService = applicationService;
        setSize(390, 290);
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getRootPane().setDefaultButton(buttonSave);

        buttonSave.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        initializeTexFields();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    private void initializeTexFields()
    {
        DialogHelper.setTextFields(textFieldName, textFieldLength, textFieldRadius, textFieldUnitPrice,
                textFieldUnitSalePrice, TableItemRightClickPopUpMenu.m_selectedMaterial, m_applicationService);
    }

    private void onOK()
    {
        var material = DialogHelper.getNewMaterialProps(textFieldName, textFieldLength,
                textFieldRadius, textFieldUnitPrice);

        if (material == null)
            return;

        DialogHelper.updateData(material, m_applicationService);
        dispose();
    }

    private void onCancel()
    {
        dispose();
    }
}
