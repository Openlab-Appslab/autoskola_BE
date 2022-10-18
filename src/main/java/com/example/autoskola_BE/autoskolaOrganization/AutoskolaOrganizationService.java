package com.example.autoskola_BE.autoskolaOrganization;

import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.CurrentUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface AutoskolaOrganizationService {

    void addOrganization(AutoskolaOrganization autoskolaOrganization, @AuthenticationPrincipal CurrentUser currentUserService);
}
