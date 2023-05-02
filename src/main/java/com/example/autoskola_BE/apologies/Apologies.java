package com.example.autoskola_BE.apologies;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne
    AutoskolaOrganization autoskolaOrganization;

}
