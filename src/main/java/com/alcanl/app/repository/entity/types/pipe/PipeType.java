package com.alcanl.app.repository.entity.types.pipe;

public enum PipeType {
    DOUBLE_PIPE("dublex boru"), SINGLE_PIPE("fırat boru");
    private final String m_value;
    PipeType(String value)
    {
        m_value = value;
    }

    @Override
    public String toString()
    {
        return m_value;
    }
}
