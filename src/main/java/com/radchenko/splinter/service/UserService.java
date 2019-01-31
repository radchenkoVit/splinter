package com.radchenko.splinter.service;

import com.radchenko.splinter.web.UserRegModel;
import com.radchenko.splinter.web.response.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> findByEmail(String email);
    void register(UserRegModel user);
}
