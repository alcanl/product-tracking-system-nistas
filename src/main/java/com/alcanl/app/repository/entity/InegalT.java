package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.entity.material.Material;

import java.math.BigDecimal;

public class InegalT extends Material {
    private final int[] m_radiusArray;
    public InegalT(int[] radiusArr, String name, BigDecimal unitPrice)
    {
        super(name, unitPrice);
        m_radiusArray = radiusArr;
    }
    public int[] getRadiusArray()
    {
        return m_radiusArray;
    }
}
