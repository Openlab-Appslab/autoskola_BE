package com.example.autoskola_BE.reservation;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationDayRepository extends CrudRepository<ReservationDay, Long> {

   Optional<ReservationDay> findById(Long id);

    ReservationDay findByReservationDate(String reservationDate);

    ReservationDay findByAutoskolaOrganization(AutoskolaOrganization autoskolaOrganization);

    List<ReservationDay> findAllByAutoskolaOrganization(AutoskolaOrganization autoskolaOrganization);

   ReservationDay findByReservationDate(ReservationDay reservationDay);

    ReservationDay findByAutoskolaOrganization(ReservationDay reservationDay);

    List<ReservationDay> findAllByReservationDateAndAutoskolaOrganization(String date, AutoskolaOrganization autoskolaOrganization);

   ReservationDay findByReservationDateAndAutoskolaOrganization(String date, AutoskolaOrganization autoskolaOrganization);

}
