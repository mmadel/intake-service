package com.cob.salesforce.controllers;

import com.cob.salesforce.models.reporting.PatientSearchCriteria;
import com.cob.salesforce.services.reports.RecommendationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/reports/recommendation")
public class PatientReportsController {

    @Autowired
    RecommendationReportService recommendationReportService;
    @ResponseBody
    @PostMapping ()
    public ResponseEntity list(@RequestBody PatientSearchCriteria patientSearchCriteria) {
        return new ResponseEntity(recommendationReportService.getReportData(patientSearchCriteria), HttpStatus.OK);
    }
}
