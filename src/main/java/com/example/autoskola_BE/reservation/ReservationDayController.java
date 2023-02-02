package com.example.autoskola_BE.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReservationDayController {

    @Autowired
    private ReservationDayServiceImpl reservationDayService;

    @GetMapping("/newReservation")
   ReservationDay addNewReservation(@RequestBody ReservationDay reservationDay){
       return reservationDayService.addNewReservation(reservationDay);
    }


}
