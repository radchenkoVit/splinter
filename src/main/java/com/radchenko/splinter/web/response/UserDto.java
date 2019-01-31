package com.radchenko.splinter.web.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<RoleDto> roles;
}
