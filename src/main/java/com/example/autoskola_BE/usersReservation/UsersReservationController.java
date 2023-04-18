package com.example.autoskola_BE.usersReservation;

import com.example.autoskola_BE.reservation.ReservationDay;
import com.example.autoskola_BE.security.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/requestForInstructor")
    List<UsersReservation> returnReservationForInstructor (@AuthenticationPrincipal CurrentUser currentUser){
        return usersReservationService.returnAllRequests(currentUser);
    }

    @GetMapping("/acceptedForInstructor")
    List<UsersReservation> returnAllAccepted (@AuthenticationPrincipal CurrentUser currentUser){
        return usersReservationService.returnAllAccepted(currentUser);
    }

    @PostMapping("/reservationAccepted")
    void allowOrDelete(@RequestBody UsersReservation usersReservation, @AuthenticationPrincipal CurrentUser currentUser){
        usersReservationService.allowOrDelete(usersReservation, currentUser);
    }

    @PostMapping("/reservationDone")
    void drivingDone(@RequestBody UsersReservation usersReservation){
        usersReservationService.drivingDone(usersReservation);
    }

}
