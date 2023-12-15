package com.alcanl.app.repository.entity.types.reduction;

public enum ReductionType {
    DEFAULT(""), WITH_SLEEVE("manşonlu redüksiyon");
    private final String m_value;

    ReductionType(String value)
    {
        m_value = value;
    }
}
