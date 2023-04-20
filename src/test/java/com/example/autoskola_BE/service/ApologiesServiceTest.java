package com.example.autoskola_BE.service;

import com.example.autoskola_BE.apologies.Apologies;
import com.example.autoskola_BE.apologies.ApologiesDto;
import com.example.autoskola_BE.apologies.ApologiesRepository;
import com.example.autoskola_BE.apologies.ApologiesServiceImpl;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ApologiesServiceTest {

    @MockBean
    private ApologiesRepository apologiesRepository;

    @Autowired
    private ApologiesServiceImpl apologiesService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void saveApology(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setAuthority("USER");
        userEntity.setEnabled(true);
        userEntity.setPassword("user");
        userEntity.setUsername("user");
        when(userRepository.save(userEntity)).thenReturn(userEntity);



        CurrentUser currentUser = new CurrentUser();
        currentUser.setAuthority("USER");
        currentUser.setEnabled(true);
        currentUser.setPassword("user");
        currentUser.setUsername("user");

        Apologies apologies = new Apologies();
        apologies.setId(1L);
        apologies.setDayOfApology("1.2.2003");
        apologies.setMessageToInstructor("Ahoj");

        ApologiesDto apologiesDto = new ApologiesDto();
        apologiesDto.setDayOfApology("1.2.2003");
        apologiesDto.setMessageToInstructor("Ahoj");

        when(apologiesRepository.save(apologies)).thenReturn(apologies);

        apologiesService.saveApology(apologiesDto, currentUser);


        verify(apologiesRepository, times(1)).save(apologies);
    }
}
