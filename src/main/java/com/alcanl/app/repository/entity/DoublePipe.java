package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.entity.material.Material;

import java.math.BigDecimal;

public class DoublePipe extends Material {
    private double m_radius;
    private String m_length;
    public DoublePipe(int radius, String length, String name, BigDecimal unitPrice)
    {
        super(name, unitPrice);
        m_radius = radius;
        m_length = length;
    }
    public double getRadius() {
        return m_radius;
    }

    public void setRadius(int m_radius) {
        this.m_radius = m_radius;
    }

    public String getLength() {
        return m_length;
    }

    public void setLength(String length) {
        this.m_length = length;
    }
}
