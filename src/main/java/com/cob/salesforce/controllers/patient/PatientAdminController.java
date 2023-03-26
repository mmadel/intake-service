package com.cob.salesforce.controllers.patient;

import com.cob.salesforce.models.validation.PatientFields;
import com.cob.salesforce.services.patient.admin.validation.PatientValidationFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/patient/admin")
public class PatientAdminController {

    @Autowired
    PatientValidationFieldService patientValidationFieldService;

    @PostMapping(path = "/change/requires/fields")
    public ResponseEntity changePatientValidationFields(@RequestBody PatientFields model) {
        return new ResponseEntity(patientValidationFieldService.change(model), HttpStatus.OK);
    }
    @CrossOrigin
    @ResponseBody
    @GetMapping(path = "/retrieve/requires/fields")
    public ResponseEntity getPatientValidationFields() {
        return new ResponseEntity(patientValidationFieldService.get(), HttpStatus.OK);
    }
}
