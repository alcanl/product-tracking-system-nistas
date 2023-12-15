package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.entity.types.fork.ForkType;
import com.alcanl.app.repository.entity.material.Material;

import java.math.BigDecimal;

public class ForkPipe extends Material {
    private ForkType m_forkType;
    private String m_length;
    public ForkPipe(ForkType forkType, String length, String name, BigDecimal unitPrice)
    {
        super(name, unitPrice);
        m_forkType = forkType;
        m_length = length;
    }
    public ForkType getForkType() {
        return m_forkType;
    }

    public void setForkType(ForkType m_forkType) {
        this.m_forkType = m_forkType;
    }

    public String getLength() {
        return m_length;
    }

    public void setLength(String m_length) {
        this.m_length = m_length;
    }
}
