package com.example.autoskola_BE.autoskolaQuestion;

import com.example.autoskola_BE.autoskolaTest.AutoskolaTest;
import com.example.autoskola_BE.imageOfQuestion.ImageOfQuestion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AutoskolaQuestionRepository extends CrudRepository<AutoskolaQuestion, Long> {

    AutoskolaQuestion findAutoskolaQuestionByTextOfQuestion(String text);

    List<AutoskolaQuestion> findAllByAutoskolaTest(AutoskolaTest autoskolaTest);

    Optional<AutoskolaQuestion> findTopByOrderByIdDesc();
}
