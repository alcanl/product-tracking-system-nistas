package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.material.Material;
import com.alcanl.app.repository.entity.types.pipe.PipeType;

import java.math.BigDecimal;

public class Pipe extends Material {
    private double m_radius;
    private PipeType m_pipeType;
    public Pipe(PipeType pipeType, double radius, String length, BigDecimal unitPrice)
    {
        super(unitPrice, length);
        m_pipeType = pipeType;
        m_radius = radius;

    }
    public double getRadius() {
        return m_radius;
    }

    public void setRadius(int m_radius) {
        this.m_radius = m_radius;
    }
    public PipeType getPipeType()
    {
        return m_pipeType;
    }
    public void setPipeType(PipeType pipeType)
    {
        m_pipeType = pipeType;
    }

}
