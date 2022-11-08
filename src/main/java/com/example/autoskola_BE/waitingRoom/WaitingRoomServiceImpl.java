package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class WaitingRoomServiceImpl implements WaitingRoomService {

    @Autowired
    private WaitingRoomRepository waitingRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveToWaitingRoom(WaitingRoom waitingRoom, @AuthenticationPrincipal CurrentUser currentUserService) {

        waitingRoom.setUserEntity(userRepository.findByUsername(currentUserService.getUsername()));
        waitingRoomRepository.save(waitingRoom);
    }
}
