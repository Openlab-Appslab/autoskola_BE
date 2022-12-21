package com.example.autoskola_BE.service;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganizationRepository;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganizationService;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AutoskolaOrganizationServiceTest {

    @Autowired
    private AutoskolaOrganizationService autoskolaOrganizationService;

    @MockBean
    private AutoskolaOrganizationRepository repository;

    @Test
    @DisplayName("Test if organization is added")
    void testAddOrganization() {

        AutoskolaOrganization autoskolaOrganization = new AutoskolaOrganization();
        autoskolaOrganization.setId_organization(1L);
        autoskolaOrganization.setName_organization("Test");
        autoskolaOrganization.setPrice_organization(500L);
        autoskolaOrganization.setDescription_organization("Test");
        repository.save(autoskolaOrganization);

        when(repository.save(autoskolaOrganization)).thenReturn(autoskolaOrganization);

        CurrentUser user = new CurrentUser();
        user.setUsername("Test");
        user.setPassword("Test");

        Optional<AutoskolaOrganization> returnedOrganization = Optional.ofNullable(autoskolaOrganizationService.addOrganization(autoskolaOrganization, user));

        Assertions.assertTrue(returnedOrganization.isPresent(), "Organization was  save");
        Assertions.assertFalse(false, "Organization is null");
    }

    @Test
    @DisplayName("Test if all organizations are returned")
    public void testReturnAllOrganization() {
        List<AutoskolaOrganization> listOfOrganization = new java.util.ArrayList<>(List.of(new AutoskolaOrganization()));
        AutoskolaOrganization autoskolaOrganization = new AutoskolaOrganization();
        autoskolaOrganization.setId_organization(1L);
        autoskolaOrganization.setName_organization("Test");
        autoskolaOrganization.setPrice_organization(500L);
        autoskolaOrganization.setDescription_organization("Test");
        doReturn(listOfOrganization).when(repository).findAll();

        List<AutoskolaOrganization> returnedOrganization = autoskolaOrganizationService.returnAllOrganization();

        Assertions.assertEquals(1, returnedOrganization.size(), "findAll should return 1 organization");
 }

}
