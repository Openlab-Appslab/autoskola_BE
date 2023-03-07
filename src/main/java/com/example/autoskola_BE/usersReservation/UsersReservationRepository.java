package com.example.autoskola_BE.usersReservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersReservationRepository extends CrudRepository<UsersReservation, Long> {

//    List<UsersReservation> findAllBy
}
