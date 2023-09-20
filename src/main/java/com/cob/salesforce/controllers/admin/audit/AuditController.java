package com.cob.salesforce.controllers.admin.audit;

import com.cob.salesforce.models.admin.audit.AuditModel;
import com.cob.salesforce.models.admin.audit.AuditResponse;
import com.cob.salesforce.services.audit.ClinicAuditService;
import com.cob.salesforce.services.audit.InsuranceCompanyAuditService;
import com.cob.salesforce.services.audit.PatientAuditService;
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
    ClinicAuditService clinicAuditService;
    @Autowired
    InsuranceCompanyAuditService insuranceCompanyAuditService;
    @Autowired
    PatientAuditService patientAuditService;

    @GetMapping(path = "/retrieve/uuid/{uuid}")
    @ResponseBody
    public ResponseEntity retrieve(@PathVariable String uuid, @RequestParam(name = "currentPage") Integer currentPage,
                                   @RequestParam(name = "pageSize") Integer pageSize) {
        Integer pageSizePerEntity = pageSize % 3;
        int clinicPageSize = pageSizePerEntity;
        int insuranceCompanyPageSize = pageSizePerEntity;
        int patientPageSize = pageSizePerEntity;
        if (clinicPageSize + insuranceCompanyPageSize + patientPageSize > pageSize)
            patientPageSize = patientPageSize - 1;
        AuditResponse clinicAuditResponse = clinicAuditService.getByEntityAndUUID(uuid, currentPage, clinicPageSize);
        AuditResponse insuranceCompanyAuditResponse = insuranceCompanyAuditService.getByEntityAndUUID(uuid, currentPage, insuranceCompanyPageSize);
        AuditResponse patientAuditResponse = patientAuditService.getByEntityAndUUID(uuid, currentPage, patientPageSize);
        List<AuditModel> auditModels = new ArrayList<>();
        auditModels.addAll(clinicAuditResponse.getAuditModels());
        auditModels.addAll(insuranceCompanyAuditResponse.getAuditModels());
        auditModels.addAll(patientAuditResponse.getAuditModels());
        AuditResponse auditResponse = AuditResponse.builder()
                .count(clinicAuditResponse.getCount() + insuranceCompanyAuditResponse.getCount() + patientAuditResponse.getCount())
                .auditModels(auditModels)
                .build();
        return new ResponseEntity(auditResponse, HttpStatus.OK);
    }
}
