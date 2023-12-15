package com.alcanl.app.repository.material;

import java.math.BigDecimal;

public abstract class Material {
    private BigDecimal m_unitPrice;
    private String m_length;
    protected Material(BigDecimal unitPrice, String length)
    {
        m_unitPrice = unitPrice;
        setLength(length);
    }
    protected void setUnitPrice(BigDecimal value)
    {
        m_unitPrice = value;
    }
    protected BigDecimal getUnitPrice()
    {
        return m_unitPrice;
    }
    protected void setLength(String length)
    {
        var str = length.trim();

        if (str.isEmpty()) {
            m_length = "";
            return;
        }

        m_length = str;
    }
    protected String getLength()
    {
        return m_length;
    }
    protected BigDecimal calculateSalePrice(int amount)
    {
        var discountedPrice = m_unitPrice.subtract(m_unitPrice.multiply(BigDecimal.valueOf(0.18)));
        var taxAddedPrice = discountedPrice.add(discountedPrice.multiply(BigDecimal.valueOf(0.2)));

        return taxAddedPrice.add(taxAddedPrice.multiply(BigDecimal.valueOf(0.35)))
                .multiply(BigDecimal.valueOf(amount));
    }
}
