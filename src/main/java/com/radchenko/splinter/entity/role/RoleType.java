package com.radchenko.splinter.entity.role;

import java.util.Arrays;

public enum  RoleType {
    ROLE_ADMIN("ADMIN"), ROLE_USER("USER");

    private String value;

    RoleType(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }

    public static RoleType of(String name) {
        return Arrays.stream(values())
                .filter(e -> e.val().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No role with name" + name));
    }
}
