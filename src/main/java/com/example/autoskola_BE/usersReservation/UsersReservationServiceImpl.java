package com.example.autoskola_BE.usersReservation;

import com.example.autoskola_BE.reservation.ReservationDay;
import com.example.autoskola_BE.reservation.ReservationDayRepository;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UsersReservationServiceImpl implements UsersReservationService{

    @Autowired
    private UsersReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationDayRepository reservationDayRepository;

    @Override
    public void addReservation(UsersReservation reservation, CurrentUser currentUser) {

        reservation.setUserEntity(userRepository.findByUsername(currentUser.getUsername()));
        reservationRepository.save(reservation);

        ReservationDay currentReservationDay = reservationDayRepository.findByReservationDate(reservation.getReservationDay().getReservationDate());

        if (Objects.equals(reservation.getTime(), "7.00")){
            currentReservationDay.setClock7(false);
        }

        if (Objects.equals(reservation.getTime(), "9.00")){
            currentReservationDay.setClock9(false);
        }

        if (Objects.equals(reservation.getTime(), "12.00")){
            currentReservationDay.setClock12(false);
        }

        if (Objects.equals(reservation.getTime(), "15.00")){
            currentReservationDay.setClock15(false);
        }

        if (Objects.equals(reservation.getTime(), "17.00")){
            currentReservationDay.setClock17(false);
        }

        reservationDayRepository.save(currentReservationDay);
    }
}
