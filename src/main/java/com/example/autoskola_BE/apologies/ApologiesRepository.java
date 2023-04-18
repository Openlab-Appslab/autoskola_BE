package com.example.autoskola_BE.apologies;

import com.example.autoskola_BE.images.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApologiesRepository extends CrudRepository<Apologies, Long>{

}
