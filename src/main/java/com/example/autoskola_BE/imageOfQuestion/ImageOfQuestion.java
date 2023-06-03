package com.example.autoskola_BE.imageOfQuestion;

import com.example.autoskola_BE.autoskolaQuestion.AutoskolaQuestion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "imageOfQuestion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageOfQuestion {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

    @JsonIgnore
    @OneToOne(mappedBy = "imageOfQuestion")
    private AutoskolaQuestion autoskolaQuestion;

}
