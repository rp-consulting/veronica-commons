package com.rolandopalermo.facturacion.ec.common.types;

import com.rolandopalermo.facturacion.ec.common.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

public enum SriEnvironmentType {

    PRUEBAS("1"),
    PRODUCCION("2");

    private String code;

    SriEnvironmentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Optional<SriEnvironmentType> getFromCode(String code) {
        if (!StringUtils.isEmpty(code)) {
            String documentType = code.substring(8, 10);
            Optional<SriEnvironmentType> op = Stream.of(SriEnvironmentType.values())
                    .filter(p -> p.getCode().compareTo(documentType) == 0)
                    .findFirst();
            return op;
        }
        return Optional.empty();
    }

}