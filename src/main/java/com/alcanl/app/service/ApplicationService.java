package com.alcanl.app.service;

import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.RepositoryException;
import com.alcanl.app.repository.database.DBConnector;
import com.alcanl.app.repository.entity.Material;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alcanl.app.repository.database.DBConnector.*;

public class ApplicationService {
    private List<Material> materials;
    public final static ExecutorService ms_threadPool = Executors.newSingleThreadExecutor();
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

        return materials.stream().filter(m -> m.getName().toLowerCase().contains(name.toLowerCase())).toList();

    }
    public List<Material> findByLength(String length)
    {
        return materials.stream().filter(m -> m.getLength().isPresent())
                    .filter(m -> m.getLength().get().toLowerCase().contains(length.toLowerCase())).toList();
    }
    public List<Material> findByRadius(double radius)
    {

        return materials.stream().filter(m -> m.getRadius().isPresent())
                    .filter(m -> radius - m.getRadius().getAsDouble() < Resources.DOUBLE_THRESHOLD
                    && radius - m.getRadius().getAsDouble() >= 0).toList();
    }
    public List<Material> getDataFromDB()
    {
        return materials;
    }
    public void reloadList()
    {
        ms_threadPool.execute(this::loadList);
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
        catch (RepositoryException ex)
        {
            throw new ServiceException(ex.getCause());
        }
    }
    public void updateAllDataUnitPrices(double ratio)
    {
        try {
            DBConnector.updateAllDataUnitPrices(ratio);
            reloadList();
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getCause());
        }

    }
    public void saveNewData(Material material)
    {
        try {
            DBConnector.saveNewData(material);
            reloadList();
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getCause());
        }
    }
    public void deleteMaterial(Material material)
    {
        try {
            var selectedMaterial = materials.stream().filter(m -> m.equals(material)).findFirst();
            selectedMaterial.ifPresent(m -> DBConnector.deleteData(m.getId()));
            reloadList();
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getCause());
        }
    }
}
