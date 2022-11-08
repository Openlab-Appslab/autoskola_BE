package com.example.autoskola_BE.images;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

    Optional<Image> findById(Long id);

    //Nájde posledne uložený obrázok
    Optional<Image>  findTopByOrderByIdDesc();
}
