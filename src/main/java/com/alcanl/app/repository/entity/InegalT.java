package com.alcanl.app.repository.entity;

import com.alcanl.app.repository.material.Material;

import java.math.BigDecimal;

public class InegalT extends Material {
    public InegalT(String length, BigDecimal unitPrice)
    {
        super(unitPrice, length);
    }
}
