package com.example.autoskola_BE.security.rest;

import com.example.autoskola_BE.security.configuration.AppConfig;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    public UserRepository repository;

    @Autowired
    public AppConfig config;


@PostMapping("/userRegister")
public void userRegister(@RequestBody UserEntity user) {

    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(user.getUsername());
    userEntity.setPassword(config.passwordEncoder().encode(user.getPassword()));
    userEntity.setAuthority("USER");

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

}
