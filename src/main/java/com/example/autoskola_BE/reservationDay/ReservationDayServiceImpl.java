package com.example.autoskola_BE.reservationDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationDayServiceImpl implements ReservationDayService {

    @Autowired
    private ReservationDayRepository reservationDayRepository;

    @Override
    public void addNewReservation(ReservationDay reservationDay) {
        if(true){
            reservationDay.setClock7(true);
            reservationDay.setClock9(true);
            reservationDay.setClock12(true);
            reservationDay.setClock15(true);
            reservationDay.setClock17(true);
            reservationDayRepository.save(reservationDay);
        }
    }
}
