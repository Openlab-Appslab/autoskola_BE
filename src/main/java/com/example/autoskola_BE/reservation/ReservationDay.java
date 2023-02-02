package com.example.autoskola_BE.reservation;

import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.usersReservation.UsersReservation;
import com.example.autoskola_BE.waitingRoom.WaitingRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReservationDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reservationDate;
    private boolean clock7;
    private boolean clock9;
    private boolean clock12;
    private boolean clock15;
    private boolean clock17;

    @JsonIgnore
    @OneToMany(mappedBy = "reservationDay")
    private List<UsersReservation> usersReservationList;

}
