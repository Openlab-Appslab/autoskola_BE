package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface WaitingRoomService {

    void saveToWaitingRoom(WaitingRoom waitingRoom, @AuthenticationPrincipal CurrentUser currentUserService);

    void saveStudentToOrganization(AutoskolaOrganization autoskolaOrganization, UserEntity userEntity);

}
