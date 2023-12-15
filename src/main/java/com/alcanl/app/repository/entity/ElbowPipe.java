package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.entity.material.Material;
import com.alcanl.app.repository.entity.types.elbow.ElbowType;

import java.math.BigDecimal;
import java.util.OptionalInt;

public class ElbowPipe extends Material {
    private String m_length;
    private ElbowType m_elbowType;
    private int m_radius;
    public ElbowPipe(String length, ElbowType elbowType, String name, BigDecimal unitPrice, int radius)
    {
        super(name, unitPrice);
        m_length = length;
        m_elbowType = elbowType;
        m_radius = radius;
    }

    public String getLength() {
        return m_length;
    }

    public void setLength(String m_length) {
        this.m_length = m_length;
    }

    public ElbowType getElbowType()
    {
        return m_elbowType;
    }
    public void setElbowTpe(ElbowType elbowType)
    {
        m_elbowType = elbowType;
    }
    public OptionalInt getRadius()
    {
        return m_radius == 0 ? OptionalInt.empty() : OptionalInt.of(m_radius);
    }
    public void setRadius(int radius)
    {
        m_radius = radius;
    }
}
