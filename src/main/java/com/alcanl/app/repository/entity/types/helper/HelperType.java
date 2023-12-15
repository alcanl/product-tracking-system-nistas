package com.alcanl.app.repository.entity.types.helper;

public enum HelperType {
    PVC_CLEANER("pvc temizleme"), PVC_SLEEVE_FLUENT("pvc kayar manşon"), SLEEVE_NORMAL("manşon"),
    PVC_BLIND_STOPPER("pvc körtapa"), BLIND_STOPPER("körtapa"), PVC_CLAMP("pvc kelepçe"),
    ADAPTER_GASKET("adaptör conta"), FLUSHING_PIPE("sifon"), PP_PN25_PIPE("pp pn25 boru"),
    CLOSING_HEADER("kapama başlığı"), T_PIECE("t-parça"), CROSS_PIECE("istavroz"),
    DOUBLE_CLAMP("çiftli kelepçe"), ROOF_BAR_STRAIGHT("düz kavis"), ROOF_BAR_C_BRIDGE("c kavis köprü"),
    ROOF_BAR_SLEEVE("manşonlu kavis"), HALF_ROOF_BAR_SLEEVE("manşonlu yarım kavis"), LONG_BLIND_STOPPER("uzun tip körtapa");
    private final String m_name;
    HelperType(String name)
    {
        m_name = name;
    }
    @Override
    public String toString()
    {
        return m_name;
    }
}
