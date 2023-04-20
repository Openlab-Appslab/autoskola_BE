package com.example.autoskola_BE.apologies;

import com.example.autoskola_BE.security.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
public class ApologiesController {


    @Autowired
    private ApologiesServiceImpl apologiesService;

    @PostMapping("sendApologies")
        void saveApology(@RequestBody ApologiesDto apologies, @AuthenticationPrincipal CurrentUser currentUser){
            apologiesService.saveApology(apologies, currentUser);
        }
    }



