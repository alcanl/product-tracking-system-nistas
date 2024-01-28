package com.alcanl.app.application.gui.dialog;

import com.alcanl.app.application.gui.MainForm;
import com.alcanl.app.application.gui.popup.TableItemRightClickPopUpMenu;
import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.entity.Material;
import com.alcanl.app.service.ApplicationService;
import com.alcanl.app.service.ServiceException;

import javax.swing.*;
import java.math.BigDecimal;

public final class DialogHelper {
    private DialogHelper() {
    }
    private static String getMaterialName(JTextField textFieldName)
    {
        var text = textFieldName.getText();

        if (text.isEmpty()) {
            Resources.showEmptyNameTextErrorMessageDialog();
            return null;
        }

        return text;
    }
    private static String getMaterialLength(JTextField textFieldLength)
    {
        return textFieldLength.getText();
    }
    private static double getMaterialRadius(JTextField textFieldRadius)
    {
        return textFieldRadius.getText().isEmpty() ? 0.0 : Double.parseDouble(textFieldRadius.getText());
    }
    private static BigDecimal getMaterialUnitSalePrice(JTextField textFieldUnitSalePrice)
    {
        if (textFieldUnitSalePrice.getText().isEmpty()) {
            Resources.showEmptyUnitPriceTextErrorMessageDialog();
            return null;
        }

        return new BigDecimal(textFieldUnitSalePrice.getText());
    }
    public static Material getNewMaterialProps(JTextField textFieldName, JTextField textFieldLength, JTextField textFieldRadius,
                                               JTextField textFieldUnitSalePrice)
    {
        try {
            var name = getMaterialName(textFieldName);
            var length = getMaterialLength(textFieldLength);
            var radius = getMaterialRadius(textFieldRadius);
            var unitPrice = getMaterialUnitSalePrice(textFieldUnitSalePrice);

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
    public static void saveNewData(ApplicationService applicationService, Material material)
    {
        try {

            if (material == null)
                return;

            applicationService.saveNewData(material);
            MainForm.IS_LIST_CHANGE = true;


        } catch (ServiceException ex)
        {
            Resources.showUnknownErrorMessageDialog(ex.getMessage());
        }
    }
    public static void setTextFields(JTextField textFieldName, JTextField textFieldLength,
                                     JTextField textFieldRadius, JTextField textFieldUnitPrice,
                                     JTextField textFieldSalePrice, Material material,
                                     ApplicationService applicationService)
    {
        textFieldName.setText(material.getName());
        textFieldLength.setText(material.getLength().orElse(""));
        textFieldRadius.setText(String.valueOf(material.getRadius().orElse(0.0)));
        textFieldSalePrice.setText(material.getUnitPrice().toString());

        if (textFieldUnitPrice == null)
            return;

        textFieldUnitPrice.setText(applicationService.findMaterial(material) != null ?
                applicationService.findMaterial(material).getUnitPrice().toString() : Resources.NO_VALUE);
    }
    public static void updateData( Material newMaterial, ApplicationService applicationService)
    {
        var prevMaterial = applicationService.findMaterial(TableItemRightClickPopUpMenu.m_selectedMaterial);

        prevMaterial.setName(newMaterial.getName());
        prevMaterial.setLength(newMaterial.getLength().orElse(""));
        prevMaterial.setRadius(newMaterial.getRadius().orElse(0.0));
        prevMaterial.setUnitPrice(newMaterial.getUnitPrice());

        try {
            applicationService.updateData(prevMaterial);
        }
        catch (ServiceException ex)
        {
            Resources.showUnknownErrorMessageDialog(ex.getMessage());
        }
        MainForm.IS_LIST_CHANGE = true;

    }
    private static double getUpdateRatio(JTextField textField)
    {
        var ratioStr = textField.getText().trim();

        if (ratioStr.isEmpty()) {
            Resources.showUnsupportedFormatWarningMessageDialog();
            return -1D;
        }
        try {
            return Double.parseDouble(ratioStr);
        } catch (NumberFormatException ignore) {
            Resources.showUnsupportedFormatWarningMessageDialog();
            return -1D;
        }
    }
    public static void updateSingleItemUnitPriceByRatio(ApplicationService applicationService, JTextField textField)
    {
        var ratio = getUpdateRatio(textField);
        if (ratio + 1D < Resources.DOUBLE_THRESHOLD) {
            Resources.showUnsupportedFormatWarningMessageDialog();
            return;
        }

        try {
            if (Resources.showSingleMaterialUpdatePriceByRatioMessage(ratio) == JOptionPane.YES_OPTION) {
                applicationService.updateMaterialUnitPriceByRatio(TableItemRightClickPopUpMenu.m_selectedMaterial, ratio);
                MainForm.IS_LIST_CHANGE = true;
            }
        } catch (ServiceException ex) {
            Resources.showUnknownErrorMessageDialog(ex.getMessage());
        }
    }
}
