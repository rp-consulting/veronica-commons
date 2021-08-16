package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum InternalStatusType {

    CREATED(1, "CREADO"),
    POSTED(2, "ENVIADO"),
    APPLIED(3, "AUTORIZADO"),
    REJECTED(4, "RECHAZADO"),
    INVALID(5, "INVALIDO"),
    ARCHIVED(6, "ANULADO");

    private long value;
    private String description;

    InternalStatusType(long value, String description) {
        this.value = value;
        this.description = description;
    }

    public long getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<InternalStatusType> get(long value) {
        return Stream.of(InternalStatusType.values())
                .filter(p -> p.value == value)
                .findFirst();
    }

    public static Optional<InternalStatusType> get(String value) {
        return Stream.of(InternalStatusType.values())
                .filter(p -> p.description.compareToIgnoreCase(value) == 0)
                .findFirst();
    }

}
