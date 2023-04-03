package com.cob.salesforce.controllers.patient;

import com.cob.salesforce.dependency.creator.PatientPhotoUploaderService;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.services.patient.PatientCreatorService;
import com.cob.salesforce.services.patient.PatientFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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


    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody PatientDTO model) {
        return new ResponseEntity(patientCreatorService.create(model), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity list(@RequestParam(name = "offset") String offset,
                               @RequestParam(name = "limit") String limit) {
        Pageable paging = PageRequest.of(Integer.parseInt(offset), Integer.parseInt(limit));
        return new ResponseEntity(patientFinderService.list(paging), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/upload")
    public ResponseEntity uploadPhoto(@RequestParam("files") MultipartFile[] files, @RequestHeader("patientId") String patientId) throws IOException {
        patientPhotoUploaderService.upload(files, Long.parseLong(patientId));
        return new ResponseEntity(HttpStatus.OK);
    }
}
