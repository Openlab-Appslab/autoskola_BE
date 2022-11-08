package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.security.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WaitingRoomController {

    @Autowired
    private WaitingRoomServiceImpl waitingRoomService;

    @PostMapping("/saveToWaitingRoom")
    void saveToWaitingRoom(@RequestBody WaitingRoom waitingRoom, @AuthenticationPrincipal CurrentUser currentUserService){
        waitingRoomService.saveToWaitingRoom(waitingRoom, currentUserService);
    }

    }



