package com.rolandopalermo.facturacion.ec.common.types;

import java.util.Optional;
import java.util.stream.Stream;

public enum RoleType {

    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER"),
    ROLE_CLIENT("CLIENT");

    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static Optional<RoleType> getFromRole(String role) {
        Optional<RoleType> op = Stream.of(RoleType.values())
                .filter(p -> p.name().compareTo(role) == 0)
                .findFirst();
        return op;
    }

}
