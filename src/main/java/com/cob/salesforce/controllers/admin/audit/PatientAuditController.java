package com.cob.salesforce.controllers.admin.audit;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.services.audit.PatientAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/audit/patient")
public class PatientAuditController {
    @Autowired
    PatientAuditService patientAuditService;

    @GetMapping(path = "/retrieve/uuid/{uuid}")
    @ResponseBody
    public ResponseEntity retrieveByEntityAndUUID(@PathVariable String uuid) {
        return new ResponseEntity(patientAuditService.getByEntityAndUUID(Patient.class, uuid), HttpStatus.OK);
    }

    @GetMapping(path = "/retrieve")
    @ResponseBody
    public ResponseEntity retrieveByUUID(){
        return new ResponseEntity(patientAuditService.getByEntity(Patient.class), HttpStatus.OK);
    }
}
