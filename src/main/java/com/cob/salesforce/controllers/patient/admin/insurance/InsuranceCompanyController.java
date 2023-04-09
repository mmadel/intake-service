package com.cob.salesforce.controllers.patient.admin.insurance;

import com.cob.salesforce.models.admin.insurance.InsuranceCompanyModel;
import com.cob.salesforce.services.patient.admin.insurance.InsuranceCompanyCreatorService;
import com.cob.salesforce.services.patient.admin.insurance.InsuranceCompanyFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin/insurance/company")
public class InsuranceCompanyController {
    @Autowired
    InsuranceCompanyCreatorService insuranceCompanyCreatorService;

    @Autowired
    InsuranceCompanyFinderService insuranceCompanyFinderService;

    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody InsuranceCompanyModel model) {
        return new ResponseEntity(insuranceCompanyCreatorService.create(model), HttpStatus.OK);
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
