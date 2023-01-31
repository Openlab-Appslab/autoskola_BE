package com.example.autoskola_BE.reservationDay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReservationDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ReservationDate;
    private boolean clock7;
    private boolean clock9;
    private boolean clock12;
    private boolean clock15;
    private boolean clock17;


}
