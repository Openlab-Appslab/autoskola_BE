package com.example.autoskola_BE.autoskolaQuestion;

import com.example.autoskola_BE.autoskolaAnswers.AutoskolaAnswers;
import com.example.autoskola_BE.autoskolaTest.AutoskolaTest;
import com.example.autoskola_BE.imageOfQuestion.ImageOfQuestion;
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
public class AutoskolaQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String textOfQuestion;

    private Boolean containsImage = false;


    @ManyToOne
    private AutoskolaTest autoskolaTest;

    @OneToOne
    private ImageOfQuestion imageOfQuestion;


    @OneToMany(mappedBy = "autoskolaQuestion")
    private List<AutoskolaAnswers> autoskolaAnswers;


}
