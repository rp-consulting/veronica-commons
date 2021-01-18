package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum RoleEnum {

    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static Optional<RoleEnum> getFromRole(String role) {
        Optional<RoleEnum> op = Stream.of(RoleEnum.values())
                .filter(p -> p.name().compareTo(role) == 0)
                .findFirst();
        return op;
    }

}