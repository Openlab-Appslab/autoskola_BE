package com.example.autoskola_BE.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationDayServiceImpl implements ReservationDayService {

    @Autowired
    private ReservationDayRepository reservationDayRepository;

    @Override
    public ReservationDay addNewReservation(ReservationDay reservationDay) {

        List<ReservationDay> reservationDayList = reservationDayRepository.findAllByReservationDateAndAutoskolaOrganization(reservationDay.getReservationDate(), reservationDay.getAutoskolaOrganization());

        if(reservationDayList.size() == 0) {

                reservationDay.setClock7(true);
                reservationDay.setClock9(true);
                reservationDay.setClock12(true);
                reservationDay.setClock15(true);
                reservationDay.setClock17(true);
                reservationDayRepository.save(reservationDay);

        }


         return reservationDayRepository.findByReservationDateAndAutoskolaOrganization(reservationDay.getReservationDate(),reservationDay.getAutoskolaOrganization());
    }


}
