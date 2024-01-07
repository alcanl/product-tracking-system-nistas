package com.alcanl.app.application.gui.popup;

import com.alcanl.app.global.Resources;

import static com.alcanl.app.global.Resources.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TableItemRightClickPopUpMenu extends JPopupMenu implements ActionListener {
    private final JMenuItem addProductToSaleCart;
    private final JMenuItem updateSelectedRow;
    private final JMenuItem deleteSelectedRow;
    private final JMenuItem addNewProduct;
    public TableItemRightClickPopUpMenu()
    {

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
        Resources.showUnsupportedFormatWarningMessageDialog();
    }
    public void updateProductClicked()
    {
        Resources.showUnsupportedFormatWarningMessageDialog();
    }
    public void deleteProductClicked()
    {
        Resources.showUnsupportedFormatWarningMessageDialog();
    }
    public void addNewProductClicked()
    {
        Resources.showUnsupportedFormatWarningMessageDialog();
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