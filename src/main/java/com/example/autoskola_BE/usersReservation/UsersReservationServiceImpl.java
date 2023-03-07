package com.example.autoskola_BE.usersReservation;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganizationRepository;
import com.example.autoskola_BE.reservation.ReservationDay;
import com.example.autoskola_BE.reservation.ReservationDayRepository;
import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private AutoskolaOrganizationRepository autoskolaOrganizationRepository;

    @Override
    public void addReservation(UsersReservation reservation, CurrentUser currentUser) {

        reservation.setUserEntity(userRepository.findByUsername(currentUser.getUsername()));


        ReservationDay currentReservationDay = reservationDayRepository.findByReservationDateAndAutoskolaOrganization(reservation.getReservationDay().getReservationDate(), reservation.getUserEntity().getUserEntityMembers());


        if (Objects.equals(reservation.getTime(), "7.00")){
            if (currentReservationDay.isClock7()) {
                reservationRepository.save(reservation);
                currentReservationDay.setClock7(false);
            }
        }

        if (Objects.equals(reservation.getTime(), "9.00")){
            if (currentReservationDay.isClock9()) {
                reservationRepository.save(reservation);
                currentReservationDay.setClock9(false);
            }
        }

        if (Objects.equals(reservation.getTime(), "12.00")){
            if (currentReservationDay.isClock12()) {
                reservationRepository.save(reservation);
                currentReservationDay.setClock12(false);
            }
        }

        if (Objects.equals(reservation.getTime(), "15.00")){
            if (currentReservationDay.isClock15()) {
                reservationRepository.save(reservation);
                currentReservationDay.setClock15(false);
            }

        }

        if (Objects.equals(reservation.getTime(), "17.00")){
            if (currentReservationDay.isClock17()) {
                reservationRepository.save(reservation);
                currentReservationDay.setClock17(false);
            }

        }

        reservationDayRepository.save(currentReservationDay);
    }

    @Override
    public List<UsersReservation> returnAllRequests(@AuthenticationPrincipal CurrentUser currentUser){
        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername());
        AutoskolaOrganization autoskolaOrganization = autoskolaOrganizationRepository.findByUserEntity(userEntity);

        List<ReservationDay> reservationDayList =  reservationDayRepository.findAllByAutoskolaOrganization(autoskolaOrganization);

        ArrayList<UsersReservation> usersReservationArrayList = new ArrayList<>();

        for(ReservationDay reservationDay : reservationDayList){

        }

        return (List<UsersReservation>) reservationRepository.findAll();

    }
}
