package com.alcanl.app.global;

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
    public static final String DELETE_DATA = "DELETE material_info WHERE material_id=?";
    public static final String UPDATE_COLUMN_NAME = "UPDATE material_info SET material_name='?' WHERE material_id=?";
    public static final String UPDATE_COLUMN_RADIUS = "UPDATE material_info SET material_radius=? WHERE material_id=?";
    public static final String UPDATE_COLUMN_LENGTH = "UPDATE material_info SET material_length='?' WHERE material_id=?";
    public static final String UPDATE_COLUMN_UNIT_PRICE = "UPDATE material_info SET material_unit_price=? WHERE material_id=?";
    public static final String UPDATE_ALL_UNIT_PRICES = "UPDATE material_info SET material_unit_price = (material_unit_price * ?)";
    private Resources() {}

}
