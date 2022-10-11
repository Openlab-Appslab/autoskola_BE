package com.example.autoskola_BE.security.rest;

import com.example.autoskola_BE.security.configuration.AppConfig;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    public UserRepository repository;

    @Autowired
    public AppConfig config;


@PostMapping("/studentRegister")
public void userRegister(@RequestBody UserEntity user) {

    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(user.getUsername());
    userEntity.setPassword(config.passwordEncoder().encode(user.getPassword()));
    userEntity.setAuthority("STUDENT");

    repository.save(userEntity);
}

    @PostMapping("/instructorRegister")
    public void instructorRegister(@RequestBody UserEntity user) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(config.passwordEncoder().encode(user.getPassword()));
        userEntity.setAuthority("INSTRUCTOR");

        repository.save(userEntity);
    }

    @PostMapping("/adminRegister")
    public void adminRegister(@RequestBody UserEntity user) {

        if (Objects.equals(user.getConfirmPassword(), "*ADMIN123*")) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(config.passwordEncoder().encode(user.getPassword()));
            userEntity.setAuthority("ADMIN");
            userEntity.setConfirmPassword("ACCEPT ADMIN");

            repository.save(userEntity);
        }
    }
}
