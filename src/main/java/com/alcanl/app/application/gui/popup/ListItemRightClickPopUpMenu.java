package com.alcanl.app.application.gui.popup;

import com.alcanl.app.repository.entity.SaleItem;
import com.alcanl.app.service.ApplicationService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.alcanl.app.global.Resources.*;

public class ListItemRightClickPopUpMenu extends JPopupMenu implements ActionListener {
    private final JMenuItem updateSelectedRow;
    private final JMenuItem deleteSelectedRow;
    private final ApplicationService m_applicationService;
    public static SaleItem m_selectedSaleItem;
    public ListItemRightClickPopUpMenu(ApplicationService applicationService)
    {
        m_applicationService = applicationService;
        updateSelectedRow = new JMenuItem(UPDATE_SELECTED_ITEM_AMOUNT);
        deleteSelectedRow = new JMenuItem(DELETE_SELECTED_PRODUCT);
        setSize(375, 200);
        initializeMenu();
    }

    private void initializeMenu()
    {
        updateSelectedRow.addActionListener(this);
        deleteSelectedRow.addActionListener(this);

        add(updateSelectedRow);
        add(deleteSelectedRow);
    }

    public void updateProductClicked()
    {
        var newAmount = showAmountInputDialog();

        if (newAmount == -1)
            return;

        m_applicationService.refreshSaleItemAmount(m_selectedSaleItem, newAmount);
    }
    public void deleteProductClicked()
    {
        m_applicationService.deleteItemFromBasket(m_selectedSaleItem);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        if (menuItem.equals(deleteSelectedRow))
            deleteProductClicked();
        else
            updateProductClicked();
    }
}
