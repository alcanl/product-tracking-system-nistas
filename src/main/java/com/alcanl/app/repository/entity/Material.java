package com.alcanl.app.repository.entity;

import static com.alcanl.app.global.Resources.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;

public class Material {
    private int m_id;
    private String m_name;
    private Double m_radius;
    private String m_length;
    private BigDecimal m_unitPrice;
    public Material(int id, String name, Double radius, String length, double unitPrice)
    {
        this(name, radius, length, unitPrice);
        m_id = id;
    }
    public Material(String name, Double radius, String length, double unitPrice)
    {
        setName(name);
        setRadius(radius);
        setLength(length);
        setUnitPrice(unitPrice);
    }
    public void setUnitPrice(double value)
    {
        m_unitPrice = BigDecimal.valueOf(value);
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
        return String.format("Ürün adı : %s\nÜrün çapı : %s\nÜrün Uzunluk: %s\nÜrün Birim Fiyat: %s\n",
                m_name, m_radius < DOUBLE_THRESHOLD ? "Çap Bilgisi Yok" : String.format("%.02f", m_radius),
                m_length == null ? "Uzunluk Bilgisi Yok" : m_length, m_unitPrice);
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
