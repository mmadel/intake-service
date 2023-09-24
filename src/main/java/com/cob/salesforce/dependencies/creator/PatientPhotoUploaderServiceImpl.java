package com.cob.salesforce.dependencies.creator;

import com.cob.salesforce.entity.PatientGrantor;
import com.cob.salesforce.entity.PatientPhotoImage;
import com.cob.salesforce.entity.intake.PatientGrantorEntity;
import com.cob.salesforce.repositories.PatientGrantorRepository;
import com.cob.salesforce.repositories.PatientPhotoImageRepository;
import com.cob.salesforce.repositories.intake.PatientGrantorRepositoryNew;
import com.cob.salesforce.repositories.intake.PatientRepositoryNew;
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
    PatientRepositoryNew patientRepositoryNew;
    @Autowired
    PatientGrantorRepositoryNew patientGrantorRepositoryNew;

    @Override
    public void upload(MultipartFile[] files, Long patientId) {


        Arrays.asList(files).stream().forEach(multipartFile -> {
            try {

                if (multipartFile.getOriginalFilename().contains("patient")) {
                    repository.save(PatientPhotoImage.builder()
                            .name(multipartFile.getOriginalFilename())
                            .type(multipartFile.getContentType())
                            .image(multipartFile.getBytes())
                            .patient(patientRepositoryNew.findById(patientId).get())
                            .build());
                }
                if (multipartFile.getOriginalFilename().contains("guarantor")) {
                    PatientGrantorEntity toBeUpdated = patientGrantorRepositoryNew.findByPatient_Id(patientId).get();
                    if (multipartFile.getOriginalFilename().contains("Idfront")){
                        toBeUpdated.setIdFront(multipartFile.getBytes());
                        patientGrantorRepositoryNew.save(toBeUpdated);
                    }
                    if (multipartFile.getOriginalFilename().contains("Idback")){
                        toBeUpdated.setIdBack(multipartFile.getBytes());
                        patientGrantorRepositoryNew.save(toBeUpdated);
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
