package com.example.autoskola_BE.autoskolaQuestion;

import com.example.autoskola_BE.autoskolaTest.AutoskolaTest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutoskolaQuestionRepository extends CrudRepository<AutoskolaQuestion, Long> {

    AutoskolaQuestion findAutoskolaQuestionByTextOfQuestion(String text);

    List<AutoskolaQuestion> findAllByAutoskolaTest(AutoskolaTest autoskolaTest);
}
