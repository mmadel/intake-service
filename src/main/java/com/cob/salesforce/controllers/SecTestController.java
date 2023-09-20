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
        /*PatientSignatureEntity entity = new PatientSignatureEntity();
        String base64Image = model.getSignature().split(",")[1];

        entity.setSignature(javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image));
        entity.setPatientId(model.getPatientId());
        repository.save(entity);*/
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity sayGreetingUser(@PathVariable(name = "id") Long id) {
       /* PatientSignatureEntity ee = repository.findById(id).get();
        System.out.println(javax.xml.bind.DatatypeConverter.printBase64Binary(ee.getSignature()));
        PatientSignatureDTO ddd = new PatientSignatureDTO();
        ddd.setSignature(javax.xml.bind.DatatypeConverter.printBase64Binary(ee.getSignature()));*/
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin")
    public String sayGreetingAdmin() {
        return "hello,world admin..!!";
    }


}
