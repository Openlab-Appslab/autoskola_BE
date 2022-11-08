package com.example.autoskola_BE.autoskolaOrganization;

import com.example.autoskola_BE.images.ImageRepository;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.CurrentUserService;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class AutoskolaOrganizationServiceImpl implements AutoskolaOrganizationService{

    @Autowired
    private AutoskolaOrganizationRepository autoskolaOrganizationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository  imageRepository;

    @Override
    public void addOrganization(AutoskolaOrganization autoskolaOrganization, @AuthenticationPrincipal CurrentUser currentUserService) {


        Long image = imageRepository.findTopByOrderByIdDesc().get().getId();
        autoskolaOrganization.setImage(imageRepository.findById(image).get());

        autoskolaOrganization.setUserEntity(userRepository.findByUsername(currentUserService.getUsername()));
        autoskolaOrganizationRepository.save(autoskolaOrganization);
    }
}
