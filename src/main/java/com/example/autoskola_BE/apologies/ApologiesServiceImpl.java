package com.example.autoskola_BE.apologies;

import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class ApologiesServiceImpl {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApologiesRepository apologiesRepository;

    void saveApology(Apologies apologies, CurrentUser currentUser){

        UserEntity currentUserEntity = userRepository.findByUsername(currentUser.getUsername());

        Apologies apologies1 = new Apologies();
        apologies1.setMessageToInstructor(apologies1.getMessageToInstructor());
        apologies1.setUsername(currentUserEntity.getUsername());
        apologiesRepository.save(apologies1);

    }
}