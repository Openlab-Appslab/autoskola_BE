package com.example.autoskola_BE.autoskolaTest;

import org.springframework.data.repository.CrudRepository;

public interface AutoskolaTestRepository extends CrudRepository<AutoskolaTest, Long> {

    AutoskolaTest findAutoskolaTestByNameOfTest(String name);

}
