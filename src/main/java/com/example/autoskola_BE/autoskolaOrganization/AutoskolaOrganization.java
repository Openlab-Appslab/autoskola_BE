package com.example.autoskola_BE.autoskolaOrganization;


import com.example.autoskola_BE.security.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.parsers.SAXParser;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AutoskolaOrganization {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id_organization;

    private String name_organization;

    private String description_organization;


    @ManyToOne
    UserEntity userEntity;

}
