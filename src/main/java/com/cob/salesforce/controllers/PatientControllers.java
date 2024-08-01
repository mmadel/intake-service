package com.cob.salesforce.controllers;

import com.cob.salesforce.dependencies.creator.PatientPhotoUploaderService;
import com.cob.salesforce.exception.business.PatientException;
import com.cob.salesforce.models.PatientSignatureDTO;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.services.PatientSignatureService;
import com.cob.salesforce.services.intake.PatientFinderServiceNew;
import com.cob.salesforce.services.intake.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = "/patient")
public class PatientControllers {
    /*
        New Impl
     */
    @Autowired
    PatientService patientService;

    @Autowired
    PatientFinderServiceNew finder;

    @Autowired
    private PatientPhotoUploaderService patientPhotoUploaderService;

    @Autowired
    PatientSignatureService patientSignatureService;

    @PostMapping("/create-new")
    @ResponseBody
    public ResponseEntity<Long> createNew(
            @RequestPart("patient") Patient model,
            @RequestPart("files")  MultipartFile[] files
    ) throws PatientException {
        Long createdPatientId = patientService.create(model);
        patientPhotoUploaderService.upload(files, createdPatientId);
        return new ResponseEntity<>(createdPatientId, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Long> create(@RequestBody Patient model) throws PatientException {
        Long createdPatientId = patientService.create(model);
        return new ResponseEntity<>(createdPatientId, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/find/clinic/{clinicId}")
    public ResponseEntity list(@RequestParam(name = "offset") String offset,
                                                                          @RequestParam(name = "limit") String limit,
                                                                          @PathVariable(name = "clinicId") Long clinicId) {
        Pageable paging = PageRequest.of(Integer.parseInt(offset), Integer.parseInt(limit), Sort.by("createdAt").descending());
        return new ResponseEntity<>(finder.getPatients(paging, clinicId), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/upload")
    public ResponseEntity uploadPhoto(@RequestParam("files") MultipartFile[] files, @RequestHeader("patientId") String patientId) throws IOException {
        patientPhotoUploaderService.upload(files, Long.parseLong(patientId));
        return new ResponseEntity(HttpStatus.OK);
    }

//    @ResponseBody
//    @DeleteMapping("/delete/{patientId}")
//    public ResponseEntity delete(@PathVariable(name = "patientId") Long patientId) {
//        patientCreatorService.delete(patientId);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @PostMapping("/signature/upload")
    public ResponseEntity uploadPatientSignature(@RequestBody PatientSignatureDTO model) {
        patientSignatureService.upload(model);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/signature/patientId/{patientId}")
    public ResponseEntity getPatientSignature(@PathVariable(name = "patientId") Long patientId) {
        return new ResponseEntity(patientSignatureService.get(patientId), HttpStatus.OK);
    }
}
