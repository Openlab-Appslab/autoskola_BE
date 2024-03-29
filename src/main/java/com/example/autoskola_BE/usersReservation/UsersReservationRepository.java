package com.example.autoskola_BE.usersReservation;

import com.example.autoskola_BE.reservation.ReservationDay;
import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersReservationRepository extends CrudRepository<UsersReservation, Long> {

//    List<UsersReservation> findAllBy
    List<UsersReservation> findAllByReservationDayAndAccept(ReservationDay reservationDay, boolean accept);

    UsersReservation findByTimeAndReservationDay(String time, ReservationDay reservationDay);

    List<UsersReservation> findAllByUserEntity(UserEntity userEntity);
}
