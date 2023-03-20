package com.example.autoskola_BE.service;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganizationRepository;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganizationService;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganizationServiceImpl;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import com.example.autoskola_BE.waitingRoom.WaitingRoom;
import com.example.autoskola_BE.waitingRoom.WaitingRoomRepository;
import com.example.autoskola_BE.waitingRoom.WaitingRoomServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WaitingRoomServiceTest {


    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WaitingRoomRepository waitingRoomRepository;

    @Autowired
    private WaitingRoomServiceImpl waitingRoomService;



    @MockBean
    private AutoskolaOrganizationRepository autoskolaOrganizationRepository;

    @InjectMocks
    private AutoskolaOrganizationServiceImpl autoskolaOrganizationService;

    @Test
    @DisplayName("Add to waiting room")
    public void saveToWaitingRoom() {
        CurrentUser currentUserService = mock(CurrentUser.class);
        when(currentUserService.getUsername()).thenReturn("test_user");

        UserEntity user = new UserEntity();
        user.setUsername("test_user");
        when(userRepository.findByUsername("test_user")).thenReturn(user);

        WaitingRoom waitingRoom = new WaitingRoom();

        // Act
        waitingRoomService.saveToWaitingRoom(waitingRoom, currentUserService);

        // Assert
        verify(waitingRoomRepository, times(1)).save(waitingRoom);
        assertEquals(user, waitingRoom.getUserEntity());


    }

    @Test
    public void testSaveStudentToOrganization() {
        // Setup
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        AutoskolaOrganization autoskolaOrganization = new AutoskolaOrganization();
        autoskolaOrganization.setId_organization(2L);

        Optional<UserEntity> currentUser = Optional.of(userEntity);

        when(userRepository.findById(1L)).thenReturn(currentUser);

        // Execute
        waitingRoomService.saveStudentToOrganization(autoskolaOrganization, userEntity);

        // Verify
        ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userEntityCaptor.capture());

        UserEntity capturedUserEntity = userEntityCaptor.getValue();
        assertEquals(autoskolaOrganization, capturedUserEntity.getUserEntityMembers());
    }

    @Test
    public void testReturnUsersWaitingRoom() {
        // create mock current user
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAuthority("USER");
        currentUser.setUsername("testUser");
        currentUser.setPassword("password");


        // create mock user entity
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testUser");
        // create mock waiting room objects
        WaitingRoom waitingRoom1 = new WaitingRoom();
        waitingRoom1.setId_waiting(1L);
        waitingRoom1.setUserEntity(userEntity);
        WaitingRoom waitingRoom2 = new WaitingRoom();
        waitingRoom2.setId_waiting(2L);
        waitingRoom2.setUserEntity(userEntity);
        // create list of waiting room objects
        List<WaitingRoom> waitingRoomList = new ArrayList<>();
        waitingRoomList.add(waitingRoom1);
        waitingRoomList.add(waitingRoom2);
        // mock repository method calls
        when(userRepository.findByUsername("testUser")).thenReturn(userEntity);
        when(waitingRoomRepository.findAllByUserEntity(userEntity)).thenReturn(waitingRoomList);
        // call the method being tested
        List<WaitingRoom> result = waitingRoomService.returnUsersWaitingRoom(currentUser);
        // verify the results
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId_waiting());
        assertEquals(2L, result.get(1).getId_waiting());
    }
}
