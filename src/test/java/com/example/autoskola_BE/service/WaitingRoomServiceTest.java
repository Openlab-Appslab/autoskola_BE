package com.example.autoskola_BE.service;

import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import com.example.autoskola_BE.waitingRoom.WaitingRoom;
import com.example.autoskola_BE.waitingRoom.WaitingRoomRepository;
import com.example.autoskola_BE.waitingRoom.WaitingRoomServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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


}
