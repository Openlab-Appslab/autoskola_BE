package com.example.autoskola_BE.ChatContact;


import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatContactRepository extends CrudRepository<ChatContact, Long> {

    List<ChatContact> findAllBySecondUser(UserEntity secondUser);

    List<ChatContact> findAllByFirstUser(UserEntity firstUser);

  //  List<ChatContact> findAllBySecondUserAndAccepted(UserEntity secondUser, boolean accepted);

   // List<ChatContact> findAllByFirstUserAndAccepted(UserEntity firstUser, boolean accepted);

   // List<ChatContact> findAllByAcceptedAndSecondUserOrAcceptedAndFirstUser(boolean accepted, UserEntity secondUser, boolean accepted2, UserEntity firstUser);
   List<ChatContact> findAllBySecondUserOrFirstUser(UserEntity secondUser, UserEntity firstUser);

    ChatContact findByFirstUserAndSecondUser(UserEntity firstUser, UserEntity secondUser);


}
