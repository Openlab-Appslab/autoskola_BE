package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.ChatContact.ChatContact;
import com.example.autoskola_BE.ChatContact.ChatContactRepository;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganizationRepository;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WaitingRoomServiceImpl implements WaitingRoomService {

    @Autowired
    private WaitingRoomRepository waitingRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AutoskolaOrganizationRepository autoskolaOrganizationRepository;

    @Autowired
    private ChatContactRepository chatContactRepository;

    @Override
    public void saveToWaitingRoom(WaitingRoom waitingRoom, @AuthenticationPrincipal CurrentUser currentUserService) {

        waitingRoom.setUserEntity(userRepository.findByUsername(currentUserService.getUsername()));
        waitingRoomRepository.save(waitingRoom);
    }

    @Override
    public void saveStudentToOrganization(AutoskolaOrganization autoskolaOrganization, UserEntity userEntity, @AuthenticationPrincipal CurrentUser instructor) {

        Optional<UserEntity> currentUser = userRepository.findById(userEntity.getId());
        currentUser.get().setUserEntityMembers(autoskolaOrganization);
        userRepository.save(currentUser.get());

       // waitingRoomRepository.delete(waitingRoomRepository.findByUserEntity(currentUser.get()));

        ChatContact chatContact = new ChatContact();
        chatContact.setFirstUser(userRepository.findByUsername(instructor.getUsername()));
        chatContact.setSecondUser(currentUser.get());
        chatContactRepository.save(chatContact);

    }

    @Override
    public List<WaitingRoom> returnUsersWaitingRoom(@AuthenticationPrincipal CurrentUser currentUser) {
        return waitingRoomRepository.findAllByUserEntity(userRepository.findByUsername(currentUser.getUsername()));
    }

    @Override
    public void removeFromWaitingRoom(WaitingRoom waitingRoom) {
        waitingRoomRepository.delete(waitingRoom);
    }

    @Override
    public List<UserEntity> returnAllStudentsInOrganization(@AuthenticationPrincipal CurrentUser currentUser) {
        AutoskolaOrganization autoskolaOrganization = autoskolaOrganizationRepository.findByUserEntity(userRepository.findByUsername(currentUser.getUsername()));
        return userRepository.findAllByUserEntityMembers(autoskolaOrganization);
    }

    @Override
    public List<UserEntity> returnAllStudentsInWaitingRoom(@AuthenticationPrincipal CurrentUser currentUser) {
        AutoskolaOrganization autoskolaOrganization = autoskolaOrganizationRepository.findByUserEntity(userRepository.findByUsername(currentUser.getUsername()));

        ArrayList<UserEntity> userEntities = new ArrayList<>();

        List<WaitingRoom> waitingRoomList = waitingRoomRepository.findAllByAutoskolaOrganization(autoskolaOrganization);
        for (WaitingRoom waitingRoom : waitingRoomList) {
            userEntities.add(waitingRoom.getUserEntity());
        }

        return userEntities;
    }

    public Optional<AutoskolaOrganization> returnCurrentAutoskolaOrganization(@AuthenticationPrincipal CurrentUser currentUser) {
        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername());


       if(Objects.equals(userEntity.getAuthority(), "STUDENT")){
           return autoskolaOrganizationRepository.findById(userEntity.getUserEntityMembers().getId_organization());
       }
        else{
        return Optional.ofNullable(autoskolaOrganizationRepository.findByUserEntity(userEntity));
        }

    }
}
