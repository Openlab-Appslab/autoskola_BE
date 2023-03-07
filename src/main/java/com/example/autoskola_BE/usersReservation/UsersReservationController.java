package com.example.autoskola_BE.usersReservation;

import com.example.autoskola_BE.reservation.ReservationDay;
import com.example.autoskola_BE.security.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersReservationController {

    @Autowired
    private UsersReservationServiceImpl usersReservationService;

    @PostMapping("/reserveDayTime")
    void addReservation(@RequestBody UsersReservation reservation, @AuthenticationPrincipal CurrentUser currentUser){
        usersReservationService.addReservation(reservation, currentUser);
    }

    @PostMapping("/reservationForInstructor")
    List<UsersReservation> returnReservationForInstructor (@AuthenticationPrincipal CurrentUser currentUser){
        return usersReservationService.returnAllRequests(currentUser);
    }

}
