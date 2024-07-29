package com.cob.salesforce.controllers.admin.dashboard;

import com.cob.salesforce.usecase.patient.CountPatientsPerMonthBySource;
import com.cob.salesforce.usecase.patient.CountTotalPatientPerMonth;
import com.cob.salesforce.usecase.patient.DrawClinicsPatientsChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/patient/counter")
public class PatientCounterController {
    @Autowired
    CountTotalPatientPerMonth countTotalPatientPerMonth;
    @Autowired
    CountPatientsPerMonthBySource countPatientsPerMonthBySource;
    @Autowired
    DrawClinicsPatientsChart drawClinicsPatientsChart;

    @ResponseBody
    @GetMapping(path = "/monthly/{year}")
    public ResponseEntity monthly(@PathVariable Integer year) {

        return new ResponseEntity(countTotalPatientPerMonth.count(year), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/monthly/doctor/{year}")
    public ResponseEntity monthlyDoctorSource(@PathVariable Integer year) {
        return new ResponseEntity(countPatientsPerMonthBySource.countDoctorSource(year),HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/monthly/entity/{year}")
    public ResponseEntity monthlyEntitySource(@PathVariable Integer year) {
        return new ResponseEntity(countPatientsPerMonthBySource.countEntitySource(year),HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/clinic/{year}")
    public ResponseEntity getData(@PathVariable Integer year) {
        return new ResponseEntity(drawClinicsPatientsChart.getData(year), HttpStatus.OK);
    }
}
