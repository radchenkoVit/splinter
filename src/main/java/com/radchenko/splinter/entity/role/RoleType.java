package com.radchenko.splinter.entity.role;

public enum  RoleType {
    ROLE_ADMIN("ADMIN"), ROLE_USER("USER");

    private String value;

    RoleType(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }
}
