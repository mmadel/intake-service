package com.cob.salesforce.controllers.admin.audit;

import com.cob.salesforce.services.audit.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/audit")
public class AuditController {
    @Autowired
    AuditService auditService;

    @GetMapping(path = "/retrieve/entity/{entity}/uuid/{uuid}")
    @ResponseBody
    public ResponseEntity retrieveByEntityAndUUID(@PathVariable String entity, @PathVariable String uuid) throws ClassNotFoundException {
        return new ResponseEntity(auditService.getByEntityAndUUID(entity, uuid), HttpStatus.OK);
    }

    @GetMapping(path = "/retrieve/entity/{entity}")
    @ResponseBody
    public ResponseEntity retrieveByUUID(@PathVariable String entity) throws ClassNotFoundException {
        return new ResponseEntity(auditService.getByEntity(entity), HttpStatus.OK);
    }
}
