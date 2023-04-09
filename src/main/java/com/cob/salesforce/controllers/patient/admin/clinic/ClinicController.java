package com.cob.salesforce.controllers.patient.admin.clinic;

import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.services.patient.admin.clinic.ClinicCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin/clinic")
public class ClinicController {

    @Autowired
    ClinicCreatorService clinicCreatorService;
    @PostMapping
    @ResponseBody
    public ResponseEntity create(ClinicModel model) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
