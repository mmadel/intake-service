package com.cob.salesforce.controllers;

import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.repositories.intake.PatientRepositoryNew;
import com.cob.salesforce.services.intake.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecTestController {

    @Autowired
    PatientService patientService;

    /*@Autowired
    PatientSignatureRepository repository;*/
    @PostMapping("/all")
    public ResponseEntity sayGreeting(@RequestBody Patient model) {
        return new ResponseEntity(patientService.create(model), HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity sayGreetingUser(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin")
    public String sayGreetingAdmin() {
        return "hello,world admin..!!";
    }


}
