package com.elhidaja.apiselhidaja.util.enums;

public enum OptionEnum {
    ZERO(0),
    ONE(1),
    TWO(2);

    private final int value;

    OptionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
