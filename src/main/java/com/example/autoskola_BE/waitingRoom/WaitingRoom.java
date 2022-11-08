package com.example.autoskola_BE.waitingRoom;

import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.security.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WaitingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_waiting;

    @OneToOne
    private AutoskolaOrganization autoskolaOrganization;

//    @OneToMany
//    private List<UserEntity> userEntityList;

    @OneToOne
    private UserEntity userEntity;

}
