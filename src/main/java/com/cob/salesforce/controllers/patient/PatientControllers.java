package com.cob.salesforce.controllers.patient;

import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.services.patient.PatientCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/patient")
public class PatientControllers {

    @Autowired
    private PatientCreatorService patientCreatorService;
    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody PatientDTO model) {
        return new ResponseEntity(patientCreatorService.create(model), HttpStatus.OK);
    }

}
