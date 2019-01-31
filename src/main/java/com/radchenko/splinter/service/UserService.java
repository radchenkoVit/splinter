package com.radchenko.splinter.service;

import com.radchenko.splinter.web.request.UserRegModel;
import com.radchenko.splinter.web.response.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> findByEmail(String email);
    void register(UserRegModel user);
    List<UserDto> getAll();
}
