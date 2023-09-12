package com.cob.salesforce.controllers.admin.audit;

import com.cob.salesforce.entity.admin.InsuranceCompanyEntity;
import com.cob.salesforce.services.audit.InsuranceCompanyAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/audit/insurance/company")
public class InsuranceCompanyAuditController {

    @Autowired
    InsuranceCompanyAuditService insuranceCompanyAuditService;

    @GetMapping(path = "/retrieve/uuid/{uuid}")
    @ResponseBody
    public ResponseEntity retrieveByEntityAndUUID(@PathVariable String uuid) {
        return new ResponseEntity(insuranceCompanyAuditService.getByEntityAndUUID(InsuranceCompanyEntity.class, uuid), HttpStatus.OK);
    }

    @GetMapping(path = "/retrieve")
    @ResponseBody
    public ResponseEntity retrieveByUUID() throws ClassNotFoundException {
        return new ResponseEntity(insuranceCompanyAuditService.getByEntity(InsuranceCompanyEntity.class), HttpStatus.OK);
    }
}
