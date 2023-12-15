package com.alcanl.app.repository.entity.types.fork;

public enum ForkType {
    PVC_SINGLE("pvc tek çatal"), PVC_DOUBLE("pvc çift çatal"), PVC_TE("pvc te çatal");
    private final String m_value;
    ForkType(String value)
    {
        m_value = value;
    }
    @Override
    public String toString()
    {
        return m_value;
    }
}
