package com.radchenko.splinter.web.request;

import lombok.Data;

@Data
public class UserRegModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
