package com.example.autoskola_BE.usersReservation;

import com.example.autoskola_BE.reservation.ReservationDay;
import com.example.autoskola_BE.security.user.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface UsersReservationService {

    void addReservation(UsersReservation reservation, @AuthenticationPrincipal CurrentUser currentUser);

    List<UsersReservation> returnAllRequests(@AuthenticationPrincipal CurrentUser currentUser);
}
