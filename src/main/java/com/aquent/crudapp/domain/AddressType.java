package com.aquent.crudapp.domain;

import java.util.HashMap;
import java.util.Map;

public enum AddressType {
    PERSONAL("Personal"),
    PHYSICAL("Physical"),
    MAILING("Mailing"),
    ;

    private static final Map<String, AddressType> typeNameMap = new HashMap<>();
    static {
        for (AddressType type : AddressType.values()) {
            typeNameMap.put(type.getTypeName(), type);
        }
    }

    public static AddressType fromTypeName(String typeName) {
        return typeNameMap.get(typeName);
    }

    private final String typeName;

    AddressType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
