package com.cob.salesforce.controllers.admin.insurance;

import com.cob.salesforce.exception.business.ClinicException;
import com.cob.salesforce.models.admin.insurance.InsuranceCompanyModel;
import com.cob.salesforce.services.admin.insurance.InsuranceCompanyCreatorService;
import com.cob.salesforce.services.admin.insurance.InsuranceCompanyFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/insurance/company")
public class InsuranceCompanyController {
    @Autowired
    InsuranceCompanyCreatorService insuranceCompanyCreatorService;

    @Autowired
    InsuranceCompanyFinderService insuranceCompanyFinderService;

    @PostMapping(path = "/create")
    @ResponseBody
    public ResponseEntity create(@RequestBody InsuranceCompanyModel model) {
        return new ResponseEntity(insuranceCompanyCreatorService.create(model), HttpStatus.OK);
    }
    @GetMapping(path = "/check/{name}")
    public ResponseEntity checkName(@PathVariable String name) {
        return new ResponseEntity(insuranceCompanyFinderService.checkName(name), HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    @ResponseBody
    public ResponseEntity getAll() {
        return new ResponseEntity(insuranceCompanyFinderService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{insuranceCompanyId}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable Long insuranceCompanyId) {
        return new ResponseEntity(insuranceCompanyFinderService.getById(insuranceCompanyId), HttpStatus.OK);
    }
}
