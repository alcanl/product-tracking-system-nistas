package com.alcanl.app.service;

import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.RepositoryException;
import com.alcanl.app.repository.database.DBConnector;
import com.alcanl.app.repository.entity.Material;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ApplicationService {
    private ArrayList<Material> materials;

    public ApplicationService()
    {
        materials = DBConnector.getAllData();
    }
    private void reloadListCallback()
    {
        materials = DBConnector.getAllData();
    }
    public List<Material> findByName(String name)
    {
        try {
            return materials.stream().filter(m -> m.getName().equalsIgnoreCase(name)).toList();
        } catch (RepositoryException ex)
        {
            throw new ServiceException(ex.getCause());
        }
    }
    public List<Material> findByLength(String length)
    {
        try {
            return materials.stream().filter(m -> m.getLength().isPresent())
                    .filter(m -> m.getLength().get().equalsIgnoreCase(length)).toList();
        } catch (RepositoryException ex)
        {
            throw new ServiceException(ex.getCause());
        }

    }
    public List<Material> findByRadius(double radius)
    {
        try {
            return materials.stream().filter(m -> m.getRadius().isPresent())
                    .filter(m -> m.getRadius().getAsDouble() - radius < Resources.DOUBLE_THRESHOLD).toList();
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getCause());
        }
    }
    public List<Material> searchNamesByHint(String hint)
    {
        try {
            return materials.stream().filter(m -> m.getName().toLowerCase().contains(hint.toLowerCase())).toList();
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getCause());
        }
    }
    public void reloadList()
    {
        Executors.newSingleThreadExecutor().execute(this::reloadListCallback);
    }
    public void updateData(String updateInfo, int oldDataId, String newData)
    {
        try {
            switch (updateInfo.toLowerCase()) {
                case "name" -> {
                    DBConnector.updateDataName(oldDataId, newData);
                    reloadList();
                }
                case "length" -> {
                    DBConnector.updateDataLength(oldDataId, newData);
                    reloadList();
                }
                case "radius" -> {
                    DBConnector.updateDataRadius(oldDataId, Double.parseDouble(newData));
                    reloadList();
                }
                case "price" -> {
                    DBConnector.updateDataUnitPrice(oldDataId, Double.parseDouble(newData));
                    reloadList();
                }
                default -> throw new ServiceException(new Throwable("Invalid Data"));
            }

        } catch (NumberFormatException ex)
        {
            throw new ServiceException(new Throwable("Invalid Data Format"));
        }
    }
    public void updateAllDataUnitPrices(double ratio)
    {
        DBConnector.updateAllDataUnitPrices(ratio);
        reloadList();
    }
}
