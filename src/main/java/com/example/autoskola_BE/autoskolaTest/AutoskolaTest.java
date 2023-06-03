package com.example.autoskola_BE.autoskolaTest;

import com.example.autoskola_BE.autoskolaQuestion.AutoskolaQuestion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AutoskolaTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameOfTest;


    @JsonIgnore
    @OneToMany(mappedBy = "autoskolaTest")
    private List<AutoskolaQuestion> autoskolaQuestionList;

}
