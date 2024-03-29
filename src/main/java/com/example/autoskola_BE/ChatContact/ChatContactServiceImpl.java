package com.example.autoskola_BE.ChatContact;


import com.example.autoskola_BE.security.user.CurrentUser;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatContactServiceImpl {

    @Autowired
    private ChatContactRepository chatContactRepository;

    @Autowired
    private UserRepository userRepository;

    /*
   void addChatContact(ChatContact chatContact, CurrentUser currentUser) {
       UserEntity me = userRepository.findByUsername(currentUser.getUsername());
         UserEntity other = userRepository.findByUsername(chatContact.getSecondUser().getUsername());
       if(chatContactRepository.findByFirstUserAndSecondUser(me, other) == null && chatContactRepository.findByFirstUserAndSecondUser(other, me) == null) {
           chatContact.setFirstUser(userRepository.findByUsername(currentUser.getUsername()));
           chatContact.setSecondUser(userRepository.findByUsername(chatContact.getSecondUser().getUsername()));
         //  chatContact.setAccepted(false);
           chatContactRepository.save(chatContact);
       }
    }
*/
    /*
    //accept chat contact
    void acceptChatContact(ChatContact chatContact, CurrentUser currentUser) {

        ChatContact chatContact1 = chatContactRepository.findById(chatContact.getId()).get();
        chatContact1.setAccepted(true);
        chatContactRepository.save(chatContact1);
    }
    */
    /*
    //delete chat contact
    void deleteChatContact(ChatContact chatContact, CurrentUser currentUser) {
        chatContact.setFirstUser(userRepository.findByUsername(currentUser.getUsername()));
        chatContact.setSecondUser(userRepository.findByUsername(chatContact.getSecondUser().getUsername()));
        chatContactRepository.delete(chatContact);
    }
    */
    /*
    List<ChatContact> getChatContacts(CurrentUser currentUser) {
        return chatContactRepository.findAllBySecondUserAndAccepted(userRepository.findByUsername(currentUser.getUsername()), false);
    }
    */
    List<ChatContact> getChatContactsAccepted(CurrentUser currentUser) {
       UserEntity user = userRepository.findByUsername(currentUser.getUsername());

        return chatContactRepository.findAllBySecondUserOrFirstUser(user, user);

    }

}
