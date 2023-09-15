package com.cob.salesforce.controllers;

import com.cob.salesforce.models.PatientSignatureDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class SecTestController {

    /*@Autowired
    PatientSignatureRepository repository;*/
    @PostMapping("/all")
    public ResponseEntity sayGreeting(@RequestBody PatientSignatureDTO model) throws UnsupportedEncodingException {
        return new ResponseEntity(HttpStatus.OK);
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
