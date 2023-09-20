package com.cob.salesforce.controllers.admin.audit;

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
    public ResponseEntity retrieveByEntityAndUUID(@PathVariable String uuid, @RequestParam(name = "offset") Integer offset,
                                                  @RequestParam(name = "limit") Integer limit) {
        return new ResponseEntity(patientAuditService.getByEntityAndUUID(uuid,offset ,limit), HttpStatus.OK);
    }

    @GetMapping(path = "/retrieve")
    @ResponseBody
    public ResponseEntity retrieveByUUID(@RequestParam(name = "offset") Integer offset,
                                         @RequestParam(name = "limit") Integer limit) {
        return new ResponseEntity(patientAuditService.getByEntity(offset, limit), HttpStatus.OK);
    }
}
