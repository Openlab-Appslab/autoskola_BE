package com.example.autoskola_BE.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationDayRepository extends CrudRepository<ReservationDay, Long> {

   Optional<ReservationDay> findById(Long id);

    ReservationDay findByReservationDate(String reservationDate);

    //find by name



}
