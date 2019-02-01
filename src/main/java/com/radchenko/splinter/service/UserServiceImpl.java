package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.User;
import com.radchenko.splinter.exceptions.NotFoundEntityException;
import com.radchenko.splinter.repository.UserRepository;
import com.radchenko.splinter.web.request.UserRegModel;
import com.radchenko.splinter.web.response.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override//TODO: refactor --> just try to make code looks better
    @Transactional(readOnly = true)
    public Optional<UserDto> findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return Optional.of(mapper.map(user, UserDto.class));
        }

        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
       return userRepository.findById(id)
               .map(u -> mapper.map(u, UserDto.class))
               .orElseThrow(() -> new NotFoundEntityException(format("user with id: %s not found", id)));
    }

    @Transactional
    public void register(UserRegModel user) {
        User eUser = mapper.map(user, User.class);
        eUser.setPassword(passwordEncoder.encode(eUser.getPassword()));
        userRepository.save(eUser);
    }

    @Transactional
    public void update(UserDto userdto) {
        Optional<User> uEntityOptional = userRepository.findById(userdto.getId());
        if (!uEntityOptional.isPresent()){
            throw new NotFoundEntityException(format("user with id: %s not found", userdto.getId()));
        }
        User uEntity = uEntityOptional.get();
        mapper.map(userdto, uEntity);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> mapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }
}
