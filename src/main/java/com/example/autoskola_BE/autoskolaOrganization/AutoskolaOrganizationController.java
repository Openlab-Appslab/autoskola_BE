package com.example.autoskola_BE.autoskolaOrganization;

import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoskolaOrganizationController {

    @Autowired
    private AutoskolaOrganizationServiceImpl autoskolaOrganizationService;

    @PostMapping
    void createOraganization(@RequestBody AutoskolaOrganization autoskolaOrganization, @AuthenticationPrincipal CurrentUser user){
        autoskolaOrganizationService.addOrganization(autoskolaOrganization, user);
    }
}
