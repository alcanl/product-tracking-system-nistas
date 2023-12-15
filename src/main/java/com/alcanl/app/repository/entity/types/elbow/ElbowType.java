package com.alcanl.app.repository.entity.types.elbow;

public enum ElbowType {
    PVC_OPENED("pvc açık dirsek"), PVC_CLOSED("pvc açık dirsek"), OPENED("açık dirsek"),
    TAILED_OPENED("kuyruklu açık dirsek"), TAILED_CLOSED("kuyruklu kapalı dirsek"), CLOSED("kapalı dirsek");
    private final String m_value;
    ElbowType(String value)
    {
        m_value = value;
    }
    @Override
    public String toString()
    {
        return m_value;
    }
}
