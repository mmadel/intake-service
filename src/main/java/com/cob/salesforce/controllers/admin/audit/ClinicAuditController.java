package com.cob.salesforce.controllers.admin.audit;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.audit.CustomRevisionEntity;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.services.audit.ClinicAuditService;
import org.hibernate.envers.DefaultRevisionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/audit/clinic")
public class ClinicAuditController {

    @Autowired
    ClinicAuditService clinicAuditService;

    @Autowired
    ClinicRepository clinicRepository;

    @GetMapping(path = "/retrieve/uuid/{uuid}")
    @ResponseBody
    public ResponseEntity retrieveByEntityAndUUID(@PathVariable String uuid, @RequestParam(name = "offset") Integer offset,
                                                  @RequestParam(name = "limit") Integer limit) {
        return new ResponseEntity(clinicAuditService.getByEntityAndUUID(uuid, offset, limit), HttpStatus.OK);
    }

    @GetMapping(path = "/retrieve")
    @ResponseBody
    public ResponseEntity retrieveByUUID(@RequestParam(name = "offset") Integer offset,
                                         @RequestParam(name = "limit") Integer limit) throws ClassNotFoundException {
        return new ResponseEntity(clinicAuditService.getByEntity(offset, limit), HttpStatus.OK);
    }

//    @GetMapping(path = "/test/retrieve")
//    @ResponseBody
//    public ResponseEntity test(@RequestParam(name = "offset") String offset,
//                               @RequestParam(name = "limit") String limit) {
//        return new ResponseEntity(clinicAuditService.get(offset, limit), HttpStatus.OK);
//    }
}
