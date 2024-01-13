package com.alcanl.app.application.gui.popup;

import com.alcanl.app.application.gui.MainForm;
import com.alcanl.app.application.gui.dialog.DialogAddNewMaterial;
import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.entity.Material;
import com.alcanl.app.service.ApplicationService;

import static com.alcanl.app.global.Resources.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableItemRightClickPopUpMenu extends JPopupMenu implements ActionListener {
    private final JMenuItem addProductToSaleCart;
    private final JMenuItem updateSelectedRow;
    private final JMenuItem deleteSelectedRow;
    private final JMenuItem addNewProduct;
    private final ApplicationService m_applicationService;
    public static Material m_selectedMaterial;
    public TableItemRightClickPopUpMenu(ApplicationService applicationService)
    {
        m_applicationService = applicationService;
        addProductToSaleCart = new JMenuItem(ADD_PRODUCT_TO_SALE_CART);
        updateSelectedRow = new JMenuItem(UPDATE_SELECTED_PRODUCT);
        deleteSelectedRow = new JMenuItem(DELETE_SELECTED_PRODUCT);
        addNewProduct = new JMenuItem(ADD_NEW_PRODUCT);
        setSize(375, 250);
        initializeMenu();
    }

    private void initializeMenu()
    {
        addNewProduct.addActionListener(this);
        addProductToSaleCart.addActionListener(this);
        updateSelectedRow.addActionListener(this);
        deleteSelectedRow.addActionListener(this);

        add(addProductToSaleCart);
        add(updateSelectedRow);
        add(deleteSelectedRow);
        addSeparator();
        add(addNewProduct);
    }

    public void addProductToCartClicked()
    {

    }
    public void updateProductClicked()
    {
        Resources.showUnsupportedFormatWarningMessageDialog();
    }
    public void deleteProductClicked()
    {
        if (Resources.showEnsureWarningMessageDialog() == JOptionPane.YES_OPTION) {
            m_applicationService.deleteMaterial(m_selectedMaterial);
            MainForm.IS_LIST_CHANGE = true;
        }

    }
    public void addNewProductClicked()
    {
        var addNewMaterialDialog = new DialogAddNewMaterial(m_applicationService);
        addNewMaterialDialog.setLocationRelativeTo(null);
        addNewMaterialDialog.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        if (menuItem.equals(addNewProduct))
            addNewProductClicked();
        else if (menuItem.equals(deleteSelectedRow))
            deleteProductClicked();
        else if (menuItem.equals(updateSelectedRow))
            updateProductClicked();
        else
            addProductToCartClicked();
    }
}