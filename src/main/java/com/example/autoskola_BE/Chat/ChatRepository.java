package com.example.autoskola_BE.Chat;

import com.example.autoskola_BE.security.user.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Long> {

    List<Chat> findAllByUserSender_UsernameIgnoreCaseAndUserReceiver_UsernameIgnoreCase(String username1, String username2);

    List<Chat> findAllByIdentificator(String identificator);

    Chat findTopByUserSender_UsernameIgnoreCaseAndUserReceiver_UsernameIgnoreCase(String username1, String username2);

    List<Chat> findAllByUserSender(UserEntity userEntity);

    List<Chat> findAllByUserReceiver(UserEntity userEntity);
}
