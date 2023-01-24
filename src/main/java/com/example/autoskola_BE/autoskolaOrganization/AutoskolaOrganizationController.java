package com.example.autoskola_BE.autoskolaOrganization;

import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.CurrentUserService;
import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AutoskolaOrganizationController {

    @Autowired
    private AutoskolaOrganizationServiceImpl autoskolaOrganizationService;

    @PostMapping("/createOrganization")
    void createOraganization(@RequestBody AutoskolaOrganization autoskolaOrganization, @AuthenticationPrincipal CurrentUser user) {
        autoskolaOrganizationService.addOrganization(autoskolaOrganization, user);
    }

    @GetMapping("allOrganization")
    public List<AutoskolaOrganization> returnAllOrganization() {
        return autoskolaOrganizationService.returnAllOrganization();
    }

    @PostMapping("/degreaseTheoryHours")
    void degreaseStudentsHours(@RequestBody UserEntity userEntity, @AuthenticationPrincipal CurrentUser currentUser){
        autoskolaOrganizationService.degreaseStudentHours(userEntity, currentUser);
    }

    @PostMapping("/degreaseDrivingHours")
    void degreaseStudentsDrivingHours(@RequestBody UserEntity userEntity, @AuthenticationPrincipal CurrentUser currentUser){
        autoskolaOrganizationService.degreaseStudentDrivingHours(userEntity, currentUser);
    }
}
