package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genero {
    MASCULINO,
    FEMENINO,
    OTRO;

    @JsonCreator
    public static Genero fromString(String key) {
        return key == null ? null : Genero.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
