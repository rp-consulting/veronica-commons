package com.rolandopalermo.facturacion.ec.common.types;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public enum WithholdingType {
    RENTA(1, "RENTA"),
    IVA(2, "IVA"),
    ISD(6, "ISD");

    private long value;
    private String description;

    WithholdingType(long value, String description) {
        this.value = value;
        this.description = description;
    }

    public static Optional<WithholdingType> get(long value) {
        return Stream.of(WithholdingType.values())
                .filter(p -> p.value == value)
                .findFirst();
    }
}
