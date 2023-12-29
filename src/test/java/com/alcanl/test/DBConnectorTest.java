package com.alcanl.test;

import com.alcanl.app.repository.database.DBConnector;
import com.alcanl.app.repository.entity.Material;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.util.List;
@Ignore
public class DBConnectorTest {
    private int count;
    @Before
    public void setUp()
    {
        List<Material> arrayList = DBConnector.getAllData();
        count = arrayList.size();
    }
    @Test
    public void saveNewDataTest()
    {
        DBConnector.saveNewData(new Material("TEST", 10.0, "120/80", 19.99));
        Assert.assertEquals(count + 1, DBConnector.getAllData().size());
    }
    @Test
    public void deleteDataTest()
    {
        DBConnector.deleteData("TEST");
        Assert.assertEquals(count, DBConnector.getAllData().size());
    }
}
