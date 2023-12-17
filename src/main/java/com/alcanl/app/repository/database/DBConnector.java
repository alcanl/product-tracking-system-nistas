package com.alcanl.app.repository.database;

import static com.alcanl.app.global.Resources.*;

import com.alcanl.app.repository.RepositoryException;
import com.alcanl.app.repository.entity.Material;
import com.karandev.util.console.Console;
import java.sql.*;
import java.util.ArrayList;

public final class DBConnector {
    private DBConnector() {}
    private static ArrayList<Material> runSelectAllQuery(Connection connection) throws SQLException {

        var arrayList = new ArrayList<Material>();
        var resultSet = connection.createStatement().executeQuery(SELECT_ALL_DATA);

        while (resultSet.next()) {
            var material = new Material(resultSet.getInt(COLUMN_ID),
                    resultSet.getString(COLUMN_NAME),
                    resultSet.getDouble(COLUMN_RADIUS),
                    resultSet.getString(COLUMN_LENGTH),
                    resultSet.getDouble(COLUMN_UNIT_PRICE));

            arrayList.add(material);
        }
        return arrayList;
    }
    private static boolean updateDoubleData(int id, String command, double newValue)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {

            var statement = connection.prepareStatement(command);
            statement.setDouble(1, newValue);

            if (id != 0)
                statement.setInt(2, id);

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            throw new RepositoryException(ex.getCause());
        }
    }
    private static boolean updateStringData(int id, String command, String newValue)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {

            var statement = connection.prepareStatement(command);
            statement.setString(1, newValue);
            statement.setInt(2, id);

            statement.executeUpdate();
            return true;

        } catch (SQLException ex)
        {
            throw new RepositoryException(ex.getCause());
        }
    }
    public static boolean deleteData(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {

            var statement = connection.prepareStatement(DELETE_DATA);
            statement.setInt(1, id);
            statement.executeUpdate();

            return true;

        } catch (SQLException ex) {
            throw new RepositoryException(ex.getCause());
        }
    }
    public static ArrayList<Material> getWholeData()
    {
        try (Connection connection = DriverManager.getConnection(DB_URL)){

            return runSelectAllQuery(connection);

        }
        catch (SQLException ex)
        {
            Console.writeLine(ex.getMessage());
            return null;
        }

    }
    public static boolean saveData(Material material)
    {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {

            var statement = connection.prepareStatement(ADD_NEW_DATA);

            statement.setString(1, material.getName());
            statement.setDouble(2, material.getRadius().orElse(0.0));
            statement.setString(3, material.getLength().orElse(null));
            statement.setDouble(4, material.getUnitPrice().doubleValue());

            statement.executeUpdate();

            return true;
        }
        catch(SQLException ex)
        {
            throw new RepositoryException(ex.getCause());
        }
    }
    public static boolean updateDataName(int id, String newName)
    {
        return updateStringData(id, UPDATE_COLUMN_NAME, newName);
    }
    public static boolean updateDataLength(int id, String newLength)
    {
        return updateStringData(id, UPDATE_COLUMN_LENGTH, newLength);
    }
    public static boolean updateDataRadius(int id, double newRadius)
    {
        return updateDoubleData(id, UPDATE_COLUMN_RADIUS, newRadius);
    }
    public static boolean updateDataUnitPrice(int id, double newUnitPrice)
    {
        return updateDoubleData(id, UPDATE_COLUMN_UNIT_PRICE, newUnitPrice);
    }
    //increases all data prices as ratio value
    public static boolean updateAllDataUnitPrice(double ratio)
    {
        return updateDoubleData(0, UPDATE_ALL_UNIT_PRICES, ratio);
    }
}
