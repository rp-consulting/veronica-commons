package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum VariableType {

    STRING(1),
    XPATH(2),
    PREDEFINED(3);

    private int type;

    VariableType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static Optional<VariableType> get(int type) {
        return Stream.of(VariableType.values())
                .filter(p -> p.getType() == type)
                .findFirst();
    }

}