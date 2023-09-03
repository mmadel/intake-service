package com.cob.salesforce.controllers.admin.audit;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.services.audit.ClinicAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/audit/clinic")
public class ClinicAuditController {
    @Autowired
    ClinicAuditService auditService;

    @GetMapping(path = "/retrieve/uuid/{uuid}")
    @ResponseBody
    public ResponseEntity retrieveByEntityAndUUID(@PathVariable String uuid) throws ClassNotFoundException {
        return new ResponseEntity(auditService.getByEntityAndUUID(ClinicEntity.class, uuid), HttpStatus.OK);
    }

    @GetMapping(path = "/retrieve")
    @ResponseBody
    public ResponseEntity retrieveByUUID() throws ClassNotFoundException {
        return new ResponseEntity(auditService.getByEntity(ClinicEntity.class), HttpStatus.OK);
    }
}