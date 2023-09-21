package com.cob.salesforce.controllers.admin;

import com.cob.salesforce.models.intake.fields.PatientField;
import com.cob.salesforce.services.admin.validation.PatientValidationFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/requires/fields")
public class PatientInputsRequirementController {

    @Autowired
    PatientValidationFieldService patientValidationFieldService;

    @PostMapping(path = "/change")
    public ResponseEntity changePatientValidationFields(@RequestBody PatientField model) {
        return new ResponseEntity(patientValidationFieldService.change(model), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/retrieve/clinicId/{clinicId}")
    public ResponseEntity getPatientRequiredFields(@PathVariable Long clinicId) {
        return new ResponseEntity(patientValidationFieldService.get(clinicId), HttpStatus.OK);
    }
}
