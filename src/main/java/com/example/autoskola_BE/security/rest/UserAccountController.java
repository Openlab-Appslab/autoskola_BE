package com.example.autoskola_BE.security.rest;

import com.example.autoskola_BE.security.configuration.AppConfig;
import com.example.autoskola_BE.security.emailVerification.ConfirmationToken;
import com.example.autoskola_BE.security.emailVerification.ConfirmationTokenRepository;
import com.example.autoskola_BE.security.emailVerification.EmailSenderService;
import com.example.autoskola_BE.security.user.UserEntity;
import com.example.autoskola_BE.security.user.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Controller
public class UserAccountController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    public AppConfig config;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;


    @RequestMapping(value="/register", method =  RequestMethod.POST)
    public ResponseEntity<Object> registerUser(@RequestBody UserEntity user)
    {
        Map<String, Object> response = new HashMap<String, Object>();

        UserEntity existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        UserEntity existingUsername = userRepository.findByUsernameIgnoreCase(user.getUsername());

        if(existingUser != null)
        {
            response.put("status", "error");
            response.put("message", "Email už existuje!");
            return new ResponseEntity<Object>(response, HttpStatus.OK);

        }

        else if(existingUsername != null)
        {
            response.put("status", "error");
            response.put("message", "Používateľské meno už existuje!");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        else
        {
            user.setPassword(config.passwordEncoder().encode(user.getPassword()));
            user.setAuthority("USER");
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Dokončite registráciu!");
            mailMessage.setFrom("AUTOSKOLA");
            mailMessage.setText("Tu potvrďte svoju registráciu: "
                    +"http://localhost:4200/register?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);
        }

        response.put("status", "success");
        response.put("message", "Potvrď registráciu cez Email");
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }

    @GetMapping("/registrationMessage")
    public String registrationMessage() {
        return getMessage();
    }



    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            UserEntity user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
