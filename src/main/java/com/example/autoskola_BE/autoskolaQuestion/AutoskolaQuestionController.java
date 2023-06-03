package com.example.autoskola_BE.autoskolaQuestion;

import com.example.autoskola_BE.autoskolaAnswers.AutoskolaAnswers;
import com.example.autoskola_BE.autoskolaTest.AutoskolaTest;
import com.example.autoskola_BE.autoskolaTest.AutoskolaTestRepository;
import com.example.autoskola_BE.imageOfQuestion.ImageOfQuestion;
import com.example.autoskola_BE.imageOfQuestion.ImageOfQuestionRepository;
import com.example.autoskola_BE.images.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AutoskolaQuestionController {

    @Autowired
    private AutoskolaQuestionRepository autoskolaQuestionRepository;

    @Autowired
    private AutoskolaTestRepository autoskolaTestRepository;

    @Autowired
    private ImageOfQuestionRepository imageOfQuestionRepository;

    @PostMapping("/saveQuestion")
    void saveQuestion(@RequestBody AutoskolaQuestion autoskolaQuestion){

        if (autoskolaQuestion.getContainsImage()) {
            autoskolaQuestion.setContainsImage(true);
            autoskolaQuestion.setImageOfQuestion(imageOfQuestionRepository.findTopByOrderByIdDesc().get());
        }
        Optional<AutoskolaTest> autoskolaTest = autoskolaTestRepository.findById(autoskolaQuestion.getAutoskolaTest().getId());
        autoskolaQuestion.setAutoskolaTest(autoskolaTest.get());
        autoskolaQuestionRepository.save(autoskolaQuestion);

    }

    @PostMapping("/getAllQuestionsInTest")
    Iterable<AutoskolaQuestion> getAllQuestionsInTest(@RequestBody AutoskolaTest autoskolaTest){

        List<AutoskolaQuestion> autoskolaQuestionList = (List<AutoskolaQuestion>) autoskolaQuestionRepository.findAllByAutoskolaTest(autoskolaTest);

        ArrayList<AutoskolaQuestion> autoskolaQuestions = new ArrayList<>();

        for(AutoskolaQuestion autoskolaQuestion : autoskolaQuestionList){
           if (autoskolaQuestion.getContainsImage()) {
               autoskolaQuestion.setImageOfQuestion(
                       ImageOfQuestion.builder()
                               .id(autoskolaQuestion.getImageOfQuestion().getId())
                               .type(autoskolaQuestion.getImageOfQuestion().getType())
                               .image(ImageUtility.decompressImage(autoskolaQuestion.getImageOfQuestion().getImage())).build());
           }
            autoskolaQuestions.add(autoskolaQuestion);
        }

        return autoskolaQuestions;

    }
}
