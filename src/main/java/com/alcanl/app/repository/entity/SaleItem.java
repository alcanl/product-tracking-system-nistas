package com.alcanl.app.repository.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SaleItem{
    private final Material m_material;
    private int m_amount;

    private void updateAmount(int amount)
    {
        m_amount = amount;
    }
    public SaleItem(Material material, int amount)
    {
        m_material = material;
        m_amount = amount;
    }
    public void updateMaterialAmount(int amount)
    {
        updateAmount(amount);
    }
    public void increaseMaterialAmount()
    {
        updateAmount(1);
    }
    public void decreaseMaterialAmount()
    {
        updateAmount(-1);
    }
    public BigDecimal getTotalPrice()
    {
        return m_material.calculateTotalSalePrice(m_amount);
    }
    @Override
    public String toString()
    {
        return String.format("%-40s %-30s %s",m_material.getName(), m_amount, getTotalPrice().setScale(2, RoundingMode.CEILING));
    }
}
