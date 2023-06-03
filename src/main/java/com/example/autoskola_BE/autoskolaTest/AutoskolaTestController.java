package com.example.autoskola_BE.autoskolaTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AutoskolaTestController {

    @Autowired
    AutoskolaTestRepository autoskolaTestRepository;

    @PostMapping("/saveTestName")
    AutoskolaTest saveTest(@RequestBody AutoskolaTest autoskolaTest){
        autoskolaTestRepository.save(autoskolaTest);
        return autoskolaTestRepository.findAutoskolaTestByNameOfTest(autoskolaTest.getNameOfTest());
    }

    @GetMapping("/getAllTests")
    ArrayList<AutoskolaTest> getAllTests(){
        return (ArrayList<AutoskolaTest>) autoskolaTestRepository.findAll();
    }
}
