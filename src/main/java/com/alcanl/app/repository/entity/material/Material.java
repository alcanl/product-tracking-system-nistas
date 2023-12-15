package com.alcanl.app.repository.entity.material;

import java.math.BigDecimal;

public class Material {
    protected String m_name;
    protected BigDecimal m_unitPrice;
    protected Material(String name, BigDecimal unitPrice)
    {
        m_name = name;
        m_unitPrice = unitPrice;
    }
    protected void setName(String name)
    {
        m_name = name;
    }
    public String getName()
    {
        return m_name;
    }
    public void setUnitPrice(BigDecimal value)
    {
        m_unitPrice = value;
    }
    public BigDecimal getUnitPrice()
    {
        return m_unitPrice;
    }
    public BigDecimal calculateSalePrice(int amount)
    {
        var discountedPrice = m_unitPrice.subtract(m_unitPrice.multiply(BigDecimal.valueOf(0.18)));
        var taxAddedPrice = discountedPrice.add(discountedPrice.multiply(BigDecimal.valueOf(0.2)));

        return taxAddedPrice.add(taxAddedPrice.multiply(BigDecimal.valueOf(0.35)))
                .multiply(BigDecimal.valueOf(amount));
    }
}
