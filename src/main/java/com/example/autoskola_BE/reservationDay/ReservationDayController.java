package com.example.autoskola_BE.reservationDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationDayController {

    @Autowired
    private ReservationDayServiceImpl reservationDayService;

    @PostMapping("/newReservation")
     void addNewReservation(@RequestBody ReservationDay reservationDay){
        reservationDayService.addNewReservation(reservationDay);
    }
}
