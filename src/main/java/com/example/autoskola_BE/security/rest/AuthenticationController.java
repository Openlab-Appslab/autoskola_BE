package com.example.autoskola_BE.security.rest;

import com.example.autoskola_BE.security.dto.ResponseDTO;
import com.example.autoskola_BE.security.dto.UserDTO;
import com.example.autoskola_BE.security.session.InMemorySessionRegistry;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public InMemorySessionRegistry sessionRegistry;

    @Autowired
    public UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        final String sessionId = sessionRegistry.registerSession(user.getUsername());
        final String currentAuthority = userRepository.findByUsername(user.getUsername()).getAuthority();
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);
        response.setCurrentAuthority(currentAuthority);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/returnAuthority")
    public UserEntity returnAuthority(@AuthenticationPrincipal CurrentUser currentUser){
        return userRepository.findByUsername(currentUser.getUsername());
    }

}
