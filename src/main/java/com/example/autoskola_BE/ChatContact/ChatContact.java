package com.example.autoskola_BE.ChatContact;

import com.example.autoskola_BE.security.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatContact {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

     // private boolean accepted;

      @ManyToOne
      private UserEntity firstUser;

      @ManyToOne
      private UserEntity secondUser;

}
