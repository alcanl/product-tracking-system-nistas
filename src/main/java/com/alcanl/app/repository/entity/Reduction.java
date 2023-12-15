package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.material.Material;
import com.alcanl.app.repository.entity.types.reduction.ReductionType;

import java.math.BigDecimal;

public class Reduction extends Material {
    private ReductionType m_reductionType;
    public Reduction(ReductionType reductionType, String length, BigDecimal unitPrice)
    {
        super(unitPrice, length);
        m_reductionType = reductionType;
    }
    public ReductionType getReductionType()
    {
        return m_reductionType;
    }
    public void setReductionType(ReductionType reductionType)
    {
        m_reductionType = reductionType;
    }
}
