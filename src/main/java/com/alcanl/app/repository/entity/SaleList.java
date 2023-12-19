package com.alcanl.app.repository.entity;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleList {
    private final List<Pair<Material, Integer>> m_basket;
    private void updateAmount(Material material, int operation)
    {
        for (Pair<Material, Integer> pair : m_basket)
            if (pair.getKey().equals(material))
                pair.setValue(pair.getValue() + operation);
    }
    public SaleList()
    {
        m_basket = new ArrayList<>(10);
    }
    public void addMaterialToBasket(Material material, int amount)
    {
        m_basket.add(Pair.of(material, amount));
    }
    public void deleteMaterialFromBasket(Material material)
    {
        m_basket.removeIf(pair -> pair.getKey().equals(material));
    }
    public void updateMaterialAmount(int index, int amount)
    {
        m_basket.get(index).setValue(amount);
    }
    public void increaseMaterialAmount(Material material)
    {
        updateAmount(material, 1);
    }
    public void decreaseMaterialAmount(Material material)
    {
        updateAmount(material, -1);
    }
    public BigDecimal getTotalPrice()
    {
        var totalPrice = BigDecimal.ZERO;

        for (Pair<Material, Integer> pair : m_basket)
            if (pair != null)
                totalPrice = totalPrice.add(pair.getKey().calculateTotalSalePrice( pair.getValue()));

        return totalPrice;
    }

}
