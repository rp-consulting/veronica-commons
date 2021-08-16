package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum SriStatusType {

    STATUS_APPLIED("AUTORIZADO"),
    STATUS_NOT_APPLIED("NO AUTORIZADO"),
    STATUS_REJECTED("DEVUELTA"),
    STATUS_INTERNAL_ERROR("CON ERRORES"),
    STATUS_PENDING("PENDIENTE");

    private String value;

    SriStatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<SriStatusType> fromValue(String value) {
        return Stream.of(SriStatusType.values())
                .filter(p -> p.getValue().compareTo(value) == 0)
                .findFirst();
    }

}
