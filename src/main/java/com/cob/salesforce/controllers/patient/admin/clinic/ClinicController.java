package com.cob.salesforce.controllers.patient.admin.clinic;

import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.services.patient.admin.clinic.ClinicCreatorService;
import com.cob.salesforce.services.patient.admin.clinic.ClinicFinderService;
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
    @Autowired
    ClinicFinderService clinicFinderService;

    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody ClinicModel model) {
        return new ResponseEntity(clinicCreatorService.create(model), HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    @ResponseBody
    public ResponseEntity getAll() {
        return new ResponseEntity(clinicFinderService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{clinicId}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable Long clinicId) {
        return new ResponseEntity(clinicFinderService.getById(clinicId), HttpStatus.OK);
    }
}
