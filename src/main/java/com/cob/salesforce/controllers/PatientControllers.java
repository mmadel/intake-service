package com.cob.salesforce.controllers;

import com.cob.salesforce.dependencies.creator.PatientPhotoUploaderService;
import com.cob.salesforce.exception.business.PatientException;
import com.cob.salesforce.exception.response.ControllerErrorResponseAdvisor;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.services.PatientCreatorService;
import com.cob.salesforce.services.PatientFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/patient")
public class PatientControllers {

    @Autowired
    private PatientCreatorService patientCreatorService;
    @Autowired
    private PatientFinderService patientFinderService;

    @Autowired
    private PatientPhotoUploaderService patientPhotoUploaderService;

    @Autowired
    ControllerErrorResponseAdvisor controllerErrorResponseAdvisor;
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Long> create(@RequestBody PatientDTO model) {
        Long createdPatientId = 0L;
        try {
            createdPatientId = patientCreatorService.create(model);
        }catch (PatientException patientException){
            return controllerErrorResponseAdvisor.responseError(patientException);
        }
        return new ResponseEntity<>(createdPatientId, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/find/clinic/{clinicId}")
    public ResponseEntity<com.cob.salesforce.models.PatientListData> list(@RequestParam(name = "offset") String offset,
                                                                          @RequestParam(name = "limit") String limit,
                                                                          @PathVariable(name = "clinicId") Long clinicId) {
        Pageable paging = PageRequest.of(Integer.parseInt(offset), Integer.parseInt(limit));
        return new ResponseEntity<>(patientFinderService.getPatients(paging, clinicId), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/upload")
    public ResponseEntity uploadPhoto(@RequestParam("files") MultipartFile[] files, @RequestHeader("patientId") String patientId) throws IOException {
        patientPhotoUploaderService.upload(files, Long.parseLong(patientId));
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity delete(@PathVariable(name = "patientId") Long patientId) {
        patientCreatorService.delete(patientId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
