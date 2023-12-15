package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.entity.material.Material;

import java.math.BigDecimal;

public class Reduction extends Material {
    private String m_length;
    public Reduction(String length, String name, BigDecimal unitPrice)
    {
        super(name, unitPrice);
        this.m_length = length;
    }
    public String getLength() {
        return m_length;
    }
    public void setLength(String length) {
        this.m_length = length;
    }


}
