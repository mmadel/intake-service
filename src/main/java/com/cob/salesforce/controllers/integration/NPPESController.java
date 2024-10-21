package com.cob.salesforce.controllers.integration;

import com.cob.salesforce.usecase.FindProviderByFirstName;
import com.cob.salesforce.usecase.FindProviderByFullName;
import com.cob.salesforce.usecase.FindProviderByLastName;
import com.cob.salesforce.usecase.FindProviderByNPIUseCase;
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
    FindProviderByFirstName findProviderByFirstName;
    @Autowired
    FindProviderByLastName findProviderByLastName;
    @Autowired
    FindProviderByFullName findProviderByFullName;

    @GetMapping("/find/provider/npi/{npi}")
    public ResponseEntity findProviderByNPI(@PathVariable Long npi) {
        return new ResponseEntity(findProviderByNPIUseCase.find(npi), HttpStatus.OK);
    }

    @GetMapping("/find/provider/f-name/{name}")
    public ResponseEntity findProviderByFirstName(@PathVariable String name) {
        return new ResponseEntity(findProviderByFirstName.find(name), HttpStatus.OK);
    }

    @GetMapping("/find/provider/l-name/{name}")
    public ResponseEntity findProviderByLastName(@PathVariable String name) {
        return new ResponseEntity(findProviderByLastName.find(name), HttpStatus.OK);
    }

    @GetMapping("/find/provider/f-name/{firstName}/l-name/{lastName}")
    public ResponseEntity findProviderByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        return new ResponseEntity(findProviderByFullName.find(firstName, lastName), HttpStatus.OK);
    }
}
