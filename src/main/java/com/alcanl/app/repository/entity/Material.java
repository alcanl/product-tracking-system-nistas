package com.alcanl.app.repository.entity;

import static com.alcanl.app.global.Resources.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;

public class Material {
    private int m_id;
    private String m_name;
    private double m_radius;
    private String m_length;
    private BigDecimal m_unitPrice;
    public Material(int id, String name, Double radius, String length, double unitPrice)
    {
        this(name, radius, length, BigDecimal.valueOf(unitPrice));
        m_id = id;
    }
    public Material(String name, double radius, String length, BigDecimal unitPrice)
    {
        setName(name);
        setRadius(radius);
        setLength(length);
        setUnitPrice(unitPrice);
    }
    public void setUnitPrice(BigDecimal value)
    {
        m_unitPrice = value;
    }
    public BigDecimal getUnitPrice()
    {
        return m_unitPrice;
    }
    public void setLength(String length)
    {
        if (length == null) {
            m_length = "";
            return;
        }
        m_length = length.trim().toUpperCase();

    }
    public OptionalDouble getRadius()
    {
        return m_radius < DOUBLE_THRESHOLD ? OptionalDouble.empty() : OptionalDouble.of(m_radius);
    }
    public void setRadius(double radius)
    {
        m_radius = radius;
    }
    public void setName(String name)
    {
        m_name = name.trim();
    }
    public String getName()
    {
        return m_name;
    }
    public int getId()
    {
        return m_id;
    }
    public Optional<String> getLength()
    {
        return m_length.isEmpty() ? Optional.empty() : Optional.of(m_length);
    }
    public BigDecimal calculateUnitSalePrice()
    {
        var discountedPrice = m_unitPrice.subtract(m_unitPrice.multiply
                (BigDecimal.valueOf(DISCOUNT)));

        var taxAddedPrice = discountedPrice.add(discountedPrice.multiply
                (BigDecimal.valueOf(TAX)));

        return taxAddedPrice.add(taxAddedPrice.multiply(BigDecimal.valueOf(PROFIT)));
    }
    public BigDecimal calculateTotalSalePrice(int amount)
    {
        return calculateUnitSalePrice().multiply(BigDecimal.valueOf(amount));
    }
    @Override
    public String toString()
    {
        var sb = new StringBuilder();
        sb.append(m_name);
        if (m_radius > DOUBLE_THRESHOLD)
            sb.append(" | ").append(m_radius);

        if (!m_length.isEmpty())
            sb.append(" | ").append(m_length);

        return sb.toString();
    }
    @Override
    public boolean equals(Object other)
    {
        return other instanceof Material material && m_name.equalsIgnoreCase(material.m_name)
                && m_length.equalsIgnoreCase(material.m_length)
                && m_radius - material.m_radius < DOUBLE_THRESHOLD;
    }
    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.m_name);
    }
}
