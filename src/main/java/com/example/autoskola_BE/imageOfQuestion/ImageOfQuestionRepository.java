package com.example.autoskola_BE.imageOfQuestion;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageOfQuestionRepository extends CrudRepository<ImageOfQuestion, Long> {

    Optional<ImageOfQuestion> findTopByOrderByIdDesc();
}
