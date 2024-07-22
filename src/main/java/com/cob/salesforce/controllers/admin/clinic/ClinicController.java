package com.cob.salesforce.controllers.admin.clinic;

import com.cob.salesforce.exception.business.ClinicException;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.services.admin.clinic.ClinicCreatorService;
import com.cob.salesforce.services.admin.clinic.ClinicFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/clinic")
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

    @GetMapping(path = "/find/active")
    @ResponseBody
    public ResponseEntity getActive() {
        return new ResponseEntity(clinicFinderService.getActiveClinics(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{clinicId}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable Long clinicId) {
        return new ResponseEntity(clinicFinderService.getById(clinicId), HttpStatus.OK);
    }
    @ResponseBody
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody ClinicModel model) {
        return new ResponseEntity(clinicCreatorService.create(model), HttpStatus.OK);
    }
}
