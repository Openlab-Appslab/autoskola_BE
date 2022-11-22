package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public void saveStudentToOrganization(AutoskolaOrganization autoskolaOrganization, UserEntity userEntity) {

        Optional<UserEntity> currentUser = userRepository.findById(userEntity.getId());
        currentUser.get().setUserEntityMembers(autoskolaOrganization);

    }

    @Override
    public List<WaitingRoom> returnUsersWaitingRoom(@AuthenticationPrincipal CurrentUser currentUser) {
        return waitingRoomRepository.findAllByUserEntity(userRepository.findByUsername(currentUser.getUsername()));
    }

    @Override
    public void removeFromWaitingRoom(WaitingRoom waitingRoom) {
        waitingRoomRepository.delete(waitingRoom);
    }
}
