package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.PatientPhotoImage;
import com.cob.salesforce.repositories.PatientPhotoImageRepository;
import com.cob.salesforce.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class PatientPhotoUploaderServiceImpl implements PatientPhotoUploaderService {

    @Autowired
    PatientPhotoImageRepository repository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public void upload(MultipartFile[] files, Long patientId) {
        Arrays.asList(files).stream().forEach(multipartFile -> {
            try {
                repository.save(PatientPhotoImage.builder()
                        .name(multipartFile.getOriginalFilename())
                        .type(multipartFile.getContentType())
                        .image(multipartFile.getBytes())
                        .patient(patientRepository.findById(patientId).get())
                        .build());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
