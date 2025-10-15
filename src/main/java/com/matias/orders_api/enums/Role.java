//Role.java

package com.matias.orders_api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    CUSTOMER,
    BUSINESS,
    DELIVERY,
    ADMIN;

    @JsonCreator
    public static Role fromString(String value) {
        if (value == null) return null;
        return Role.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}
