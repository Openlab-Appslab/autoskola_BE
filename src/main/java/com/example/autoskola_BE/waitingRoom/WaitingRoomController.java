package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class WaitingRoomController {

    @Autowired
    private WaitingRoomServiceImpl waitingRoomService;

    @PostMapping("/saveToWaitingRoom")
    void saveToWaitingRoom(@RequestBody WaitingRoom waitingRoom, @AuthenticationPrincipal CurrentUser currentUserService) {
        waitingRoomService.saveToWaitingRoom(waitingRoom, currentUserService);
    }

    @PostMapping("/addMembersToOrganization")
    void addMembersToOrganization(@RequestBody WaitingRoom waitingRoom, @AuthenticationPrincipal CurrentUser currentUser) {
        waitingRoomService.saveStudentToOrganization(waitingRoom.getAutoskolaOrganization(), waitingRoom.getUserEntity(), currentUser);
    }


    @GetMapping("/returnAllWaitingRoom")
    List<WaitingRoom> returnAllWaitingRoom(@AuthenticationPrincipal CurrentUser currentUser) {
        return waitingRoomService.returnUsersWaitingRoom(currentUser);
    }

    @PostMapping("/removeFromWaitingRoom")
    void removeFromWaitingRoom(@RequestBody WaitingRoom waitingRoom) {
        waitingRoomService.removeFromWaitingRoom(waitingRoom);
    }

    @GetMapping("/returnAllStudentsInOrganization")
    List<UserEntity> returnAllStudentsInOrganization(@AuthenticationPrincipal CurrentUser currentUser) {
        return waitingRoomService.returnAllStudentsInOrganization(currentUser);

    }

    @GetMapping("/returnAllStudentsInWaitingRoom")
    List<UserEntity> returnAllStudentsInWaitingRoom(@AuthenticationPrincipal CurrentUser currentUser) {
        return waitingRoomService.returnAllStudentsInWaitingRoom(currentUser);

    }

    @GetMapping("/returnCurrentOrganization")
    public Optional<AutoskolaOrganization> getCurreAutoskolaOrganization(@AuthenticationPrincipal CurrentUser currentUser) {
        return waitingRoomService.returnCurrentAutoskolaOrganization(currentUser);
    }
}

