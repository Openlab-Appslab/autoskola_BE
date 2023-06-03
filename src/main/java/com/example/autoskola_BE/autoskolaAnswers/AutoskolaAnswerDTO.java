package com.example.autoskola_BE.autoskolaAnswers;

import com.example.autoskola_BE.autoskolaQuestion.AutoskolaQuestion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class AutoskolaAnswerDTO {

    private String textOfAnswer;

    private Boolean correctOrNot;

    private AutoskolaQuestion autoskolaQuestion;

}
