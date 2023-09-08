package com.cob.salesforce.controllers.admin.audit;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.InsuranceCompanyEntity;
import com.cob.salesforce.models.admin.audit.AuditModel;
import com.cob.salesforce.services.audit.ClinicAuditService;
import com.cob.salesforce.services.audit.InsuranceCompanyAuditService;
import com.cob.salesforce.services.audit.Temp;
import com.cob.salesforce.services.audit.Tempa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/audit")
public class AuditController {
    @Autowired
    Temp clinicAuditService;
    @Autowired
    Tempa insuranceCompanyAuditService;

    @GetMapping(path = "/retrieve/uuid/{uuid}")
    @ResponseBody
    public ResponseEntity retrieve(@PathVariable String uuid) {
        List<AuditModel> result = new ArrayList<>();
        result.addAll(clinicAuditService.getByEntityAndUUID(ClinicEntity.class, uuid));
        result.addAll(insuranceCompanyAuditService.getByEntityAndUUID(InsuranceCompanyEntity.class, uuid));
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
