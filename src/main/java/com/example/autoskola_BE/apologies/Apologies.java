package com.example.autoskola_BE.apologies;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Apologies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String username;

    private String messageToInstructor;

    private String dayOfApology;

}
