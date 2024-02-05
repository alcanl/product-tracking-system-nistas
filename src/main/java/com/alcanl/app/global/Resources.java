package com.alcanl.app.global;

import javax.swing.*;
import java.awt.*;

import static com.google.common.io.Resources.getResource;

public final class Resources {
    public static final double DOUBLE_THRESHOLD = 0.00005;
    public static final double DISCOUNT = 0.18;
    public static final double TAX = 0.2;
    public static final double PROFIT = 0.35;
    public static final String SELECT_ALL_DATA = "SELECT material_id, material_name, material_radius, material_length, material_unit_price, material_isPipeType FROM material_info";
    public static String DB_URL = "jdbc:sqlite::resource:file:\\Users\\alica\\Documents\\Nistas\\db\\materials.db";
    public static final String COPY_DB_URL = "alcanl\\Nistas\\db";
    public static final String COPY_IMAGE_URL = "alcanl\\Nistas\\assets\\pic";
    public static final String COPY_ASSETS_URL = "alcanl\\Nistas\\assets";
    public static final String COLUMN_ID = "material_id";
    public static final String COLUMN_NAME = "material_name";
    public static final String COLUMN_RADIUS = "material_radius";
    public static final String COLUMN_LENGTH = "material_length";
    public static final String COLUMN_UNIT_PRICE = "material_unit_price";
    public static final String COLUMN_IS_PIPE_TYPE = "material_isPipeType";
    public static final String ADD_NEW_DATA = "INSERT INTO material_info(material_name, material_radius, material_length, material_unit_price, material_isPipeType) values(?,?,?,?,?)";
    public static final String DELETE_DATA_BY_ID = "DELETE FROM material_info WHERE material_id=?";
    public static final String DELETE_DATA_BY_NAME = "DELETE FROM material_info WHERE material_name=?";
    public static final String UPDATE_COLUMN_NAME = "UPDATE material_info SET material_name=? WHERE material_id=?";
    public static final String UPDATE_COLUMN_RADIUS = "UPDATE material_info SET material_radius=? WHERE material_id=?";
    public static final String UPDATE_COLUMN_LENGTH = "UPDATE material_info SET material_length=? WHERE material_id=?";
    public static final String UPDATE_COLUMN_UNIT_PRICE = "UPDATE material_info SET material_unit_price=? WHERE material_id=?";
    public static final String UPDATE_ALL_PIPE_TYPE_UNIT_PRICES = "UPDATE material_info SET material_unit_price = (material_unit_price + (material_unit_price * ? / 100)) WHERE material_isPipeType=true";
    public static final String UPDATE_SELECTED_ITEM_UNIT_PRICE_BY_RATIO = "UPDATE material_info SET material_unit_price = (material_unit_price + (material_unit_price * ? / 100)) WHERE material_id=?";
    public static final String COMPANY_NAME = "Nistaş Yapı Malzemeleri";
    public static final String TABLE_COLUMN_HEADER_NAME = "Ürün Adı";
    public static final String TABLE_COLUMN_HEADER_LENGTH = "Ürün Uzunluk";
    public static final String TABLE_COLUMN_HEADER_RADIUS = "Ürün Çapı";
    public static final String TABLE_COLUMN_HEADER_UNIT_PRICE = "Birim Satış Fiyatı";
    public static final String NO_VALUE = "Değer Yok";
    public static final String NIMBUS_THEME = "Nimbus";
    public static final String DEFAULT_ICON = "icon.png";
    public static final String DEFAULT_LOGO = "logo.png";
    public static final String ERROR_MESSAGE = "Bilinmeyen bir hata oluştu.";
    public static final String ERROR_TITLE = "Hata";
    public static final String WARNING_TITLE = "Uyarı";
    public static final String ERROR_UNSUPPORTED_FORMAT = "Bu işlem için desteklenmeyen formatta bir değer girdiniz!";
    public static final String WARNING_EMPTY_SEARCH_LIST = "Aratılan değere ilişkin ürün bulunmamaktadır.";
    public static final String ADD_PRODUCT_TO_SALE_CART = "Sepete Ekle";
    public static final String UPDATE_SELECTED_PRODUCT = "Düzenle";
    public static final String DELETE_SELECTED_PRODUCT = "Sil";
    public static final String UPDATE_SELECTED_ITEM_AMOUNT = "Adet Güncelle";
    public static final String ADD_NEW_PRODUCT = "Yeni Ürün Ekle";
    public static final String TOTAL_TABLE_COUNT = "Toplam Ürün Sayısı : %d";
    private static final String ERROR_MESSAGE_EMPTY_NAME = "Ürün isim bilgisi boş bırakılamaz";
    private static final String ERROR_MESSAGE_EMPTY_UNIT_PRICE = "Ürün birim fiyat bilgisi boş bırakılamaz";
    private static final String DIALOG_AMOUNT_INPUT_TEXT = "Lütfen Ürün Miktarını Giriniz : ";
    private static final String DIALOG_RATIO_INPUT_TEXT = "Lütfen Tüm Ürünlere Uygulanacak Zam Oranını Giriniz : ";
    private static final String WARNING_MESSAGE_NO_SELECTED_ITEM_TEXT = "Seçili Ürün Bulunmamaktadır.";
    private static final String WARNING_MESSAGE_DELETE_ITEM = "Seçili ürünü silmek üzeresiniz. Bu işlem geri alınamaz. Devam etmek istediğinize emin misiniz?";
    private static final String WARNING_UPDATE_RATIO_TEXT = "Tüm Boru Tipi Ürünlere %%%.02f Zam Uygulanacak. Onaylıyor musunuz?";
    private static final String WARNING_UPDATE_SINGLE__RATIO_TEXT = "Seçili Ürüne %%%.02f Zam Uygulanacak. Onaylıyor musunuz?";
    private static final String WARNING_EMPTY_CART_MESSAGE = "Sepette Ürün Bulunmamaktadır.";
    private static final String AMOUNT_TITLE = "Adet Bilgisi";
    private static final String RATIO_TITLE = "Artış Oran Bilgisi";

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
    private static void setCancelButtonTR() {UIManager.put("OptionPane.cancelButtonText", "İptal");}
    private static void setYesNoButtonTR()
    {
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
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
    public static int showEnsureWarningMessageDialog()
    {
        setYesNoButtonTR();
        return JOptionPane.showConfirmDialog(null, WARNING_MESSAGE_DELETE_ITEM, WARNING_TITLE,
                JOptionPane.YES_NO_OPTION);
    }
    public static void showEmptyNameTextErrorMessageDialog()
    {
        setOkButtonTR();
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE_EMPTY_NAME, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }
    public static void showEmptyUnitPriceTextErrorMessageDialog()
    {
        setOkButtonTR();
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE_EMPTY_UNIT_PRICE, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }
    public static void showUnknownErrorMessageDialog(String errMessage)
    {
        setOkButtonTR();
        JOptionPane.showMessageDialog(null, ERROR_MESSAGE + "\n" + errMessage,
                ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }
    public static int showAmountInputDialog()
    {
        setOkButtonTR();
        setCancelButtonTR();
        var amount = JOptionPane.showInputDialog(null,DIALOG_AMOUNT_INPUT_TEXT, AMOUNT_TITLE,
                JOptionPane.PLAIN_MESSAGE, null, null, 1);
        if (amount == null)
            return -1;
        try {
            var amountInt = Integer.parseInt((String)amount);
            if (amountInt <= 0) {
                showUnsupportedFormatWarningMessageDialog();
                return -1;
            }
            else
                return amountInt;
        } catch (NumberFormatException ignore) {
            showUnsupportedFormatWarningMessageDialog();
            return -1;
        }
    }
    public static double showUpdatePriceRatioInputDialog()
    {
        setOkButtonTR();
        setCancelButtonTR();

        var ratio = JOptionPane.showInputDialog(null, DIALOG_RATIO_INPUT_TEXT,
                RATIO_TITLE, JOptionPane.PLAIN_MESSAGE);

        if (ratio == null)
            return -1D;

        try {
            var ratioDouble = Double.parseDouble(ratio);
            if (ratioDouble <= DOUBLE_THRESHOLD) {
                showUnsupportedFormatWarningMessageDialog();
                return -1D;
            }
            else
                return ratioDouble;

        } catch (NumberFormatException ignore)
        {
            showUnsupportedFormatWarningMessageDialog();
            return -1D;
        }
    }
    public static void showNoSelectedMaterialMessage()
    {
        setOkButtonTR();
        JOptionPane.showMessageDialog(null, WARNING_MESSAGE_NO_SELECTED_ITEM_TEXT, WARNING_TITLE,
                JOptionPane.WARNING_MESSAGE);
    }
    public static int showUpdatePriceByRatioMessage(double ratio)
    {
        setYesNoButtonTR();
        return JOptionPane.showConfirmDialog(null,
                String.format(WARNING_UPDATE_RATIO_TEXT, ratio), WARNING_TITLE, JOptionPane.YES_NO_OPTION);
    }
    public static int showSingleMaterialUpdatePriceByRatioMessage(double ratio)
    {

        setYesNoButtonTR();
        return JOptionPane.showConfirmDialog(null,
                String.format(WARNING_UPDATE_SINGLE__RATIO_TEXT, ratio), WARNING_TITLE, JOptionPane.YES_NO_OPTION);
    }

    public static void showWarningEmptyCartMessage()
    {
        setOkButtonTR();
        JOptionPane.showMessageDialog(null, WARNING_EMPTY_CART_MESSAGE, WARNING_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
}
