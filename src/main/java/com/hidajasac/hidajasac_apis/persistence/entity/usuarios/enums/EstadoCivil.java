package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoCivil {
    SOLTERO,
    CASADO,
    DIVORCIADO,
    VIUDO;

    @JsonCreator
    public static EstadoCivil fromString(String key) {
        return key == null ? null : EstadoCivil.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
