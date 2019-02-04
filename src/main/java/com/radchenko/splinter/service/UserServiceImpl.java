package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.User;
import com.radchenko.splinter.entity.role.RoleType;
import com.radchenko.splinter.exceptions.ActivationCodeNotFoundException;
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
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailSenderService mailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, BCryptPasswordEncoder passwordEncoder, MailSenderService mailService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
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
        eUser.addRoles(RoleType.ROLE_USER);
        eUser.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(eUser);

        //TODO: move to property file
        String messageBody = String.format("Activation code: http://localhost:8800/activate/%s", eUser.getActivationCode());
        mailService.send(user.getEmail(), "Activation Code", messageBody);
    }

    @Transactional
    public void update(UserDto userdto) {
        User uEntity = userRepository.findById(userdto.getId())
                .orElseThrow(() -> new NotFoundEntityException(format("user with id: %s not found", userdto.getId())));

        //TODO: uEntity.getRoles().clear(); need to be fixed
        mapper.map(userdto, uEntity);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> mapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto activateUser(String code) {
        User user = userRepository.findByActivationCode(code)
                .orElseThrow(() -> new ActivationCodeNotFoundException("Activation code not found"));

        user.setActive(true);
        user.setActivationCode(null);

        return mapper.map(user, UserDto.class);
    }
}
