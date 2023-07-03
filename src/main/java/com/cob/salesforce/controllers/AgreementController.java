package com.cob.salesforce.controllers;

import com.cob.salesforce.models.AgreementDTO;
import com.cob.salesforce.services.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/agreement")
public class AgreementController {

    @Autowired
    AgreementService agreementService ;

    @GetMapping
    public ResponseEntity<List<AgreementDTO>>  findAll(){
        return new ResponseEntity(agreementService.finaAll() , HttpStatus.OK);
    }
}
