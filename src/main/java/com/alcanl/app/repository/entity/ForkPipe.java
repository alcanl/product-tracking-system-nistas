package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.entity.types.fork.ForkType;
import com.alcanl.app.repository.material.Material;

import java.math.BigDecimal;

public class ForkPipe extends Material {
    private ForkType m_forkType;
    public ForkPipe(ForkType forkType, String length, BigDecimal unitPrice)
    {
        super(unitPrice, length);
        m_forkType = forkType;
    }
    public ForkType getForkType() {
        return m_forkType;
    }

    public void setForkType(ForkType m_forkType) {
        this.m_forkType = m_forkType;
    }

}
