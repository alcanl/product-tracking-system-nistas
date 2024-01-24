package com.alcanl.app.repository.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SaleItem{
    private final Material m_material;
    private int m_amount;

    private void updateAmount(int amount)
    {
        m_amount += amount;
    }
    public SaleItem(Material material, int amount)
    {
        m_material = material;
        m_amount = amount;
    }
    public void updateMaterialAmount(int amount)
    {
        m_amount = amount;
    }
    public void increaseMaterialAmount()
    {
        updateAmount(1);
    }
    public void decreaseMaterialAmount()
    {
        updateAmount(-1);
    }
    public void increaseMaterialAmount(int plusAmount)
    {
        updateAmount(plusAmount);
    }
    public void decreaseMaterialAmount(int subtractAmount)
    {
        updateAmount(-subtractAmount);
    }
    public String getName()
    {
        return m_material.toString();
    }
    public int getAmount()
    {
        return m_amount;
    }
    public BigDecimal getTotalPrice()
    {
        return m_material.calculateTotalSalePrice(m_amount);
    }
    @Override
    public String toString()
    {
        return String.format("%-37s %s Adet %12s TL",m_material, m_amount, getTotalPrice().setScale(2, RoundingMode.CEILING));
    }
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof SaleItem saleItem)
            return m_material.equals(saleItem.m_material);
        else
            return false;
    }
    @Override
    public int hashCode()
    {
        return m_material.hashCode();
    }
}
