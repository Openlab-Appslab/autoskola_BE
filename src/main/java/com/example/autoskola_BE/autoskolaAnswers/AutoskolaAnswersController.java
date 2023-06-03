package com.example.autoskola_BE.autoskolaAnswers;

import com.example.autoskola_BE.autoskolaQuestion.AutoskolaQuestion;
import com.example.autoskola_BE.autoskolaQuestion.AutoskolaQuestionRepository;
import com.example.autoskola_BE.autoskolaTest.AutoskolaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AutoskolaAnswersController {

    @Autowired
    AutoskolaAnswersRepository autoskolaAnswersRepository;

    @Autowired
    AutoskolaQuestionRepository autoskolaQuestionRepository;

    @PostMapping("/saveAnswer")
    void saveAnswer(@RequestBody AutoskolaAnswerDTO autoskolaAnswers){

        AutoskolaAnswers autoskolaAnswers1 = new AutoskolaAnswers();
        autoskolaAnswers1.setTextOfAnswer(autoskolaAnswers.getTextOfAnswer());
        autoskolaAnswers1.setCorrectOrNot(autoskolaAnswers.getCorrectOrNot());
        Optional<AutoskolaQuestion> autoskolaQuestion = autoskolaQuestionRepository.findById(autoskolaAnswers.getAutoskolaQuestion().getId());
        autoskolaAnswers1.setAutoskolaQuestion(autoskolaQuestion.get());

        autoskolaAnswersRepository.save(autoskolaAnswers1);
    }


}
