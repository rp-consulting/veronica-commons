package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum ProductType {

    PRODUCTO(1),
    SERVICIO(2);

    private int type;

    ProductType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static Optional<ProductType> getFromType(int type) {
        Optional<ProductType> op = Stream.of(ProductType.values())
                .filter(p -> p.getType() == type)
                .findFirst();
        return op;
    }

}
