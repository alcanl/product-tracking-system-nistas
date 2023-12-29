package com.alcanl.app.service;

import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.RepositoryException;
import com.alcanl.app.repository.database.DBConnector;
import com.alcanl.app.repository.entity.Material;

import java.util.List;
import java.util.concurrent.Executors;

import static com.alcanl.app.repository.database.DBConnector.*;

public class ApplicationService {
    private List<Material> materials;
    public ApplicationService()
    {
        loadList();
    }
    private void loadList()
    {
        try {
            materials = DBConnector.getAllData();
        }catch (RepositoryException ex)
        {
            throw new ServiceException(ex.getCause());
        }
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
    public List<Material> getDataFromDB()
    {
        return materials;
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
        Executors.newSingleThreadExecutor().execute(this::loadList);
    }
    public boolean updateData(String updateInfo, int oldDataId, String newData)
    {
        var isSuccessUpdate = false;

        try {
            switch (updateInfo.toLowerCase()) {
                case "name" -> {
                    isSuccessUpdate = updateDataName(oldDataId, newData);
                    reloadList();
                }
                case "length" -> {
                    isSuccessUpdate = updateDataLength(oldDataId, newData);
                    reloadList();
                }
                case "radius" -> {
                    isSuccessUpdate = updateDataRadius(oldDataId, Double.parseDouble(newData));
                    reloadList();
                }
                case "price" -> {
                    isSuccessUpdate = updateDataUnitPrice(oldDataId, Double.parseDouble(newData));
                    reloadList();
                }
                default -> throw new ServiceException(new Throwable("Invalid Data"));
            }
            return isSuccessUpdate;

        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    public void updateAllDataUnitPrices(double ratio)
    {
        DBConnector.updateAllDataUnitPrices(ratio);
        reloadList();
    }
}
