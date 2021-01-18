package com.rolandopalermo.facturacion.ec.common.types;

import com.rolandopalermo.facturacion.ec.common.exception.ResourceNotFoundException;

import java.util.stream.Stream;

import static java.lang.String.format;

public enum VariableTypeEnum {

    STRING(1),
    XPATH(2),
    PREDEFINED(3);

    private int type;

    VariableTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static VariableTypeEnum get(int type) {
        return Stream.of(VariableTypeEnum.values())
                .filter(p -> p.getType() == type)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(format("El tipo de variable %s", type)));
    }

}