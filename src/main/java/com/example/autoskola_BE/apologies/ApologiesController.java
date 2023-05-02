package com.example.autoskola_BE.apologies;

import com.example.autoskola_BE.security.user.CurrentUser;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;
import java.util.List;

@RestController
public class ApologiesController {


    @Autowired
    private ApologiesServiceImpl apologiesService;

    @PostMapping("sendApologies")
        void saveApology(@RequestBody ApologiesDto apologies, @AuthenticationPrincipal CurrentUser currentUser){
            apologiesService.saveApology(apologies, currentUser);
        }

        @GetMapping("getApologies")
        List<Apologies> apologies(@AuthenticationPrincipal CurrentUser currentUser){
         return apologiesService.getApologyByOrganiztion(currentUser);
        }
    }



