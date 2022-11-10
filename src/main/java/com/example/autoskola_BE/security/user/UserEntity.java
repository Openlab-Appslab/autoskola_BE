package com.example.autoskola_BE.security.user;


import com.example.autoskola_BE.autoskolaOrganization.AutoskolaOrganization;
import com.example.autoskola_BE.waitingRoom.WaitingRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String authority;

    @Column
    private String confirmPassword = "BASIC USER";

    @JsonIgnore
    @OneToMany(mappedBy = "userEntity")
    private Set<AutoskolaOrganization> organizationSet;

    @JsonIgnore
    @OneToOne(mappedBy = "userEntity")
    private WaitingRoom waitingRoom;

    @ManyToOne
    private AutoskolaOrganization userEntityMembers = null;

}
