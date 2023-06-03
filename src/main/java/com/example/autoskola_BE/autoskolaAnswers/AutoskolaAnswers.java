package com.example.autoskola_BE.autoskolaAnswers;

import com.example.autoskola_BE.autoskolaQuestion.AutoskolaQuestion;
import com.example.autoskola_BE.autoskolaTest.AutoskolaTest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AutoskolaAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String textOfAnswer;

    private Boolean correctOrNot;

    @JsonIgnore
    @ManyToOne
    private AutoskolaQuestion autoskolaQuestion;
}
