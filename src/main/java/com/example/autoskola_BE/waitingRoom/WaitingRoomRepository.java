package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaitingRoomRepository extends CrudRepository<WaitingRoom, Long> {

    List<WaitingRoom> findAllByUserEntity(UserEntity userEntity);

    List<WaitingRoom> findAllByAutoskolaOrganization(AutoskolaOrganization autoskolaOrganization);
}

