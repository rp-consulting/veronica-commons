package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum SourceType {

    VERONICA_WEB_APP(1, "VERONICA_WEB_APP"),
    OTHER(2, "OTHER");

    private long value;
    private String description;

    SourceType(long value, String description) {
        this.value = value;
        this.description = description;
    }

    public long getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<SourceType> get(long value) {
        return Stream.of(SourceType.values())
                .filter(p -> p.value == value)
                .findFirst();
    }

    public static Optional<SourceType> get(String value) {
        return Stream.of(SourceType.values())
                .filter(p -> p.description.compareToIgnoreCase(value) == 0)
                .findFirst();
    }

}
