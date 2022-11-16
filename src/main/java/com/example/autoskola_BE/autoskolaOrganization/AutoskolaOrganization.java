package com.example.autoskola_BE.autoskolaOrganization;


import com.example.autoskola_BE.images.Image;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.waitingRoom.WaitingRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.parsers.SAXParser;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AutoskolaOrganization {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id_organization;

    private String name_organization;

    private Long price_organization;

    private String description_organization;


    @ManyToOne
    UserEntity userEntity;

    @OneToOne
    private Image image;

    @JsonIgnore
    @OneToMany(mappedBy = "autoskolaOrganization")
    private List<WaitingRoom> waitingRoomList;

    @JsonIgnore
    @OneToMany(mappedBy = "userEntityMembers")
    private Set<UserEntity> userEntitySet;

}
