package com.example.autoskola_BE.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public CurrentUserService(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity user = repository.findByUsername(username);
        if (user != null) {
            final CurrentUser currentUser = new CurrentUser();
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());
            currentUser.setAuthority(user.getAuthority());

            return currentUser;
        }

        else {
            throw new UsernameNotFoundException("Failed to find user with username: " + username);
        }

    }
}
