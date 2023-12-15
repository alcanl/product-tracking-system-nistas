package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.material.Material;
import com.alcanl.app.repository.entity.types.elbow.ElbowType;

import java.math.BigDecimal;
import java.util.OptionalInt;

public class ElbowPipe extends Material {
    private ElbowType m_elbowType;
    private int m_radius;
    public ElbowPipe(String length, ElbowType elbowType, BigDecimal unitPrice, int radius)
    {
        super(unitPrice, length);
        m_elbowType = elbowType;
        m_radius = radius;
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
