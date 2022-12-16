package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface WaitingRoomService {

    void saveToWaitingRoom(WaitingRoom waitingRoom, @AuthenticationPrincipal CurrentUser currentUserService);

    void saveStudentToOrganization(AutoskolaOrganization autoskolaOrganization, UserEntity userEntity);

    List<WaitingRoom> returnUsersWaitingRoom(@AuthenticationPrincipal CurrentUser currentUser);

    void removeFromWaitingRoom(WaitingRoom waitingRoom);

    List<UserEntity> returnAllStudentsInWaitingRoom(@AuthenticationPrincipal CurrentUser currentUser);

}
