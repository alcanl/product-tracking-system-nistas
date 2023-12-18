package com.alcanl.app.service;

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
    public List<Material> searchByName(String name)
    {
        return materials.stream().filter(m -> m.getName().equals(name)).toList();
    }
    public List<Material> searchByLength(String length)
    {
        return materials.stream().filter(m -> m.getLength().isPresent())
                .filter(m -> m.getLength().get().equals(length)).toList();
    }
}
