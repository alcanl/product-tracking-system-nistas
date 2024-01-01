package com.alcanl.app.global;

import javax.swing.*;
import java.awt.*;

import static com.google.common.io.Resources.getResource;

public final class Resources {
    public static final double DOUBLE_THRESHOLD = 0.00005;
    public static final double DISCOUNT = 0.18;
    public static final double TAX = 0.2;
    public static final double PROFIT = 0.35;
    public static final String SELECT_ALL_DATA = "SELECT material_id, material_name, material_radius, material_length, material_unit_price FROM material_info";
    public static final String DB_URL = "jdbc:sqlite:./materials.db";
    public static final String COLUMN_ID = "material_id";
    public static final String COLUMN_NAME = "material_name";
    public static final String COLUMN_RADIUS = "material_radius";
    public static final String COLUMN_LENGTH = "material_length";
    public static final String COLUMN_UNIT_PRICE = "material_unit_price";
    public static final String ADD_NEW_DATA = "INSERT INTO material_info(material_name, material_radius, material_length, material_unit_price) values(?,?,?,?)";
    public static final String DELETE_DATA_BY_ID = "DELETE material_info WHERE material_id=?";
    public static final String DELETE_DATA_BY_NAME = "DELETE material_info WHERE material_name=?";
    public static final String UPDATE_COLUMN_NAME = "UPDATE material_info SET material_name='?' WHERE material_id=?";
    public static final String UPDATE_COLUMN_RADIUS = "UPDATE material_info SET material_radius=? WHERE material_id=?";
    public static final String UPDATE_COLUMN_LENGTH = "UPDATE material_info SET material_length='?' WHERE material_id=?";
    public static final String UPDATE_COLUMN_UNIT_PRICE = "UPDATE material_info SET material_unit_price=? WHERE material_id=?";
    public static final String UPDATE_ALL_UNIT_PRICES = "UPDATE material_info SET material_unit_price = (material_unit_price * ?)";
    public static final String COMPANY_NAME = "Nistaş Yapı Malzemeleri";
    public static final String TABLE_COLUMN_HEADER_NAME = "Ürün Adı";
    public static final String TABLE_COLUMN_HEADER_LENGTH = "Ürün Uzunluk";
    public static final String TABLE_COLUMN_HEADER_RADIUS = "Ürün Çapı";
    public static final String TABLE_COLUMN_HEADER_UNIT_PRICE = "Birim Satış Fiyatı";
    public static final String NO_VALUE = "Değer Yok";
    public static final String NIMBUS_THEME = "Nimbus";
    public static final String METAL_THEME = "Metal";
    public static final String WINDOWS_THEME = "Windows";
    public static final String DEFAULT_THEME = "Windows Classic";
    public static final String DEFAULT_ICON = "icon.png";
    public static final String DEFAULT_LOGO = "logo.png";
    public static final String ERROR_MESSAGE = "Bilinmeyen bir hata oluştu.";
    public static final String ERROR_TITLE = "Hata";
    public static final String WARNING_TITLE = "Uyarı";
    public static final String ERROR_UNSUPPORTED_FORMAT = "Bu işlem için desteklenmeyen formatta bir değer girdiniz!";
    public static final String WARNING_EMPTY_SEARCH_LIST = "Aratılan değere ilişkin ürün bulunmamaktadır.";

    private Resources() {}
    public static void setLayout(String theme) {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (theme.equals(info.getName()))
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                         IllegalAccessException ex) {
                    JOptionPane.showMessageDialog(null, ERROR_MESSAGE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                }

        }
    }
    public static void initializeLogo(JLabel label)
    {
        var icon  = new ImageIcon(getResource(DEFAULT_LOGO));
        var image = icon.getImage();
        var imageScale = image.getScaledInstance(250, 140, Image.SCALE_SMOOTH);
        var scaledIcon = new ImageIcon(imageScale);
        label.setIcon(scaledIcon);
    }
    private static void setOkButtonTR()
    {
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }
    public static void showUnsupportedFormatWarningMessageDialog()
    {
        setOkButtonTR();
        JOptionPane.showMessageDialog(null, ERROR_UNSUPPORTED_FORMAT, ERROR_TITLE,
                JOptionPane.ERROR_MESSAGE);
    }
    public static void showEmptyListWarningMessageDialog()
    {
        setOkButtonTR();
        JOptionPane.showMessageDialog(null, WARNING_EMPTY_SEARCH_LIST, WARNING_TITLE,
                JOptionPane.INFORMATION_MESSAGE);

    }

}
