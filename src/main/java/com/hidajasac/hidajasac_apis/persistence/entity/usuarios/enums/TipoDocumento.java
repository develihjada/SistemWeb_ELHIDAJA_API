package com.hidajasac.hidajasac_apis.persistence.entity.usuarios.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoDocumento {
    NUMERICO {
        @Override
        public boolean validar(String valor) {
            return valor != null && valor.matches("\\d+");
        }
    },
    ALFANUMERICO {
        @Override
        public boolean validar(String valor) {
            return valor != null && valor.matches("[a-zA-Z0-9]+");
        }
    },
    ALFABETICO {
        @Override
        public boolean validar(String valor) {
            return valor != null && valor.matches("[a-zA-Z]+");
        }
    };

    @JsonCreator
    public static TipoDocumento fromString(String key) {
        return key == null ? null : TipoDocumento.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
    public abstract boolean validar(String valor);
}
