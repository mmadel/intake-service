package com.cob.salesforce.controllers.integration;

import com.cob.salesforce.usecase.FindProviderByNPIUseCase;
import com.cob.salesforce.usecase.FindProviderByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/nppes")
public class NPPESController {
    @Autowired
    FindProviderByNPIUseCase findProviderByNPIUseCase;
    @Autowired
    FindProviderByName findProviderByName;

    @GetMapping("/find/provider/npi/{npi}")
    public ResponseEntity findProviderByNPI(@PathVariable Long npi){
        return new ResponseEntity(findProviderByNPIUseCase.find(npi), HttpStatus.OK);
    }
    @GetMapping("/find/provider/name/{name}")
    public ResponseEntity findProviderByName(@PathVariable String name){
        return new ResponseEntity(findProviderByName.find(name), HttpStatus.OK);
    }
}
