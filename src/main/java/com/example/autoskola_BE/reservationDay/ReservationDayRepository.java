package com.example.autoskola_BE.reservationDay;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationDayRepository extends CrudRepository<ReservationDay, Long> {

//Optional <ReservationDay> findByReservationDate(String reservation);


}
