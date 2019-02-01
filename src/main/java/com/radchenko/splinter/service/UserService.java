package com.radchenko.splinter.service;

import com.radchenko.splinter.web.request.UserRegModel;
import com.radchenko.splinter.web.response.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> findByEmail(String email);
    UserDto findById(Long id);
    void register(UserRegModel user);
    void update(UserDto user);
    List<UserDto> getAll();
}
