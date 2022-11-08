package com.example.autoskola_BE.waitingRoom;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingRoomRepository extends CrudRepository<WaitingRoom, Long> {
}

