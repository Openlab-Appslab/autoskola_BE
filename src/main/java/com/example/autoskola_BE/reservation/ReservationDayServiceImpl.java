package com.example.autoskola_BE.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationDayServiceImpl implements ReservationDayService {

    @Autowired
    private ReservationDayRepository reservationDayRepository;

    @Override
    public ReservationDay addNewReservation(ReservationDay reservationDay) {

        if(reservationDayRepository.findByReservationDate(reservationDay.getReservationDate()) == null){
            reservationDay.setClock7(true);
            reservationDay.setClock9(true);
            reservationDay.setClock12(true);
            reservationDay.setClock15(true);
            reservationDay.setClock17(true);
            reservationDayRepository.save(reservationDay);
        }

        return reservationDayRepository.findByReservationDate(reservationDay.getReservationDate());
    }


}
