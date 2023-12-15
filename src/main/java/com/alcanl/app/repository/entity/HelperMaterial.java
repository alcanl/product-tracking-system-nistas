package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.entity.types.helper.HelperType;
import com.alcanl.app.repository.entity.material.Material;

import java.math.BigDecimal;

public class HelperMaterial extends Material {
    private int m_radius;
    private final HelperType m_helperType;

    protected HelperMaterial(String name, BigDecimal unitPrice, int radius, HelperType helperType)
    {
        super(name, unitPrice);
        m_radius = radius;
        m_helperType = helperType;
    }
    public int getRadius()
    {
        return m_radius;
    }
    public void setRadius(int m_radius)
    {
        this.m_radius = m_radius;
    }
    public HelperType getHelperType()
    {
        return m_helperType;
    }
}
