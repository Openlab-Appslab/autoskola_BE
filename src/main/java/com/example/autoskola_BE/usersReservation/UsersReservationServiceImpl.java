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
                reservation.setAccept(false);
                reservationRepository.save(reservation);
                currentReservationDay.setClock7(false);
            }
        }

        if (Objects.equals(reservation.getTime(), "9.00")){
            if (currentReservationDay.isClock9()) {
                reservation.setAccept(false);
                reservationRepository.save(reservation);
                currentReservationDay.setClock9(false);
            }
        }

        if (Objects.equals(reservation.getTime(), "12.00")){
            if (currentReservationDay.isClock12()) {
                reservation.setAccept(false);
                reservationRepository.save(reservation);
                currentReservationDay.setClock12(false);
            }
        }

        if (Objects.equals(reservation.getTime(), "15.00")){
            if (currentReservationDay.isClock15()) {
                reservation.setAccept(false);
                reservationRepository.save(reservation);
                currentReservationDay.setClock15(false);
            }

        }

        if (Objects.equals(reservation.getTime(), "17.00")){
            if (currentReservationDay.isClock17()) {
                reservation.setAccept(false);
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

        List<UsersReservation> currentList =  reservationRepository.findAllByReservationDayAndAccept(reservationDay, false);
            usersReservationArrayList.addAll(currentList);
        }

        return usersReservationArrayList;

    }

    public List<UsersReservation> returnAllAccepted(@AuthenticationPrincipal CurrentUser currentUser) {
        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername());
        AutoskolaOrganization autoskolaOrganization = autoskolaOrganizationRepository.findByUserEntity(userEntity);

        List<ReservationDay> reservationDayList = reservationDayRepository.findAllByAutoskolaOrganization(autoskolaOrganization);

        ArrayList<UsersReservation> usersReservationArrayList = new ArrayList<>();

        for (ReservationDay reservationDay : reservationDayList) {

            List<UsersReservation> currentList = reservationRepository.findAllByReservationDayAndAccept(reservationDay, true);
            usersReservationArrayList.addAll(currentList);
        }

        return usersReservationArrayList;
    }
    public void allowOrDelete(UsersReservation usersReservation, CurrentUser currentUser){

        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername());

        AutoskolaOrganization autoskolaOrganization = autoskolaOrganizationRepository.findByUserEntity(userEntity);

        ReservationDay currentReservationDay = reservationDayRepository.findByReservationDateAndAutoskolaOrganization(usersReservation.getReservationDay().getReservationDate(), autoskolaOrganization);

        System.out.println(currentReservationDay);


        if (usersReservation.isAccept()){
            UsersReservation usersReservation1 = reservationRepository.findByTimeAndReservationDay(usersReservation.getTime(), usersReservation.getReservationDay());
            usersReservation1.setAccept(true);

            reservationRepository.save(usersReservation1);
        }

        else{

            if (Objects.equals(usersReservation.getTime(), "7.00")){
                if (!currentReservationDay.isClock7()) {
                    currentReservationDay.setClock7(true);
                }
            }

            if (Objects.equals(usersReservation.getTime(), "9.00")){
                if (!currentReservationDay.isClock9()) {
                    currentReservationDay.setClock9(true);
                }
            }

            if (Objects.equals(usersReservation.getTime(), "12.00")){
                if (!currentReservationDay.isClock12()) {
                    currentReservationDay.setClock12(true);
                }
            }

            if (Objects.equals(usersReservation.getTime(), "15.00")){
                if (!currentReservationDay.isClock15()) {
                    currentReservationDay.setClock15(true);
                }

            }

            if (Objects.equals(usersReservation.getTime(), "17.00")){
                if (!currentReservationDay.isClock17()) {
                    currentReservationDay.setClock17(true);
                }

            }

            reservationDayRepository.save(currentReservationDay);
            reservationRepository.deleteById(usersReservation.getId());

        }
    }

    public void drivingDone(UsersReservation usersReservation){


       UserEntity currentStudent = userRepository.findByUsername(usersReservation.getUserEntity().getUsername());
       currentStudent.setCountOfDriving(currentStudent.getCountOfDriving() - 2);
       userRepository.save(currentStudent);

       reservationRepository.deleteById(usersReservation.getId());



    }


   public List<UsersReservation> getInfoForStudent(CurrentUser currentUser){

        UserEntity userEntity = userRepository.findByUsername(currentUser.getUsername());

        return reservationRepository.findAllByUserEntity(userEntity);
   }
}
