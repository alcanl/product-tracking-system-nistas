package com.alcanl.app.service;

import com.alcanl.app.global.Resources;
import com.alcanl.app.repository.database.DBConnector;
import com.alcanl.app.repository.entity.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationService {
    private boolean isDataUpdated;
    private ArrayList<Material> materials;

    public ApplicationService()
    {
        materials = DBConnector.getAllData();
    }
    public void setUpdated(boolean isUpdated)
    {
        isDataUpdated = isUpdated;
    }
    public boolean getUpdated()
    {
        return isDataUpdated;
    }
    public List<Material> findByName(String name)
    {
        return materials.stream().filter(m -> m.getName().equalsIgnoreCase(name)).toList();
    }
    public List<Material> findByLength(String length)
    {
        return materials.stream().filter(m -> m.getLength().isPresent())
                .filter(m -> m.getLength().get().equalsIgnoreCase(length)).toList();
    }
    public List<Material> findByRadius(double radius)
    {
        return materials.stream().filter(m -> m.getRadius().isPresent())
                .filter(m -> m.getRadius().getAsDouble() - radius < Resources.DOUBLE_THRESHOLD).toList();
    }
    public List<Material> searchNamesByHint(String hint)
    {
        return materials.stream().filter(m -> m.getName().toLowerCase().contains(hint.toLowerCase())).toList();
    }
}
