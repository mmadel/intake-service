package com.cob.salesforce.services.patient;

import com.cob.salesforce.entity.PatientPhotoImage;
import com.cob.salesforce.repositories.patient.PatientPhotoImageRepository;
import com.cob.salesforce.repositories.patient.PatientRepository;
import com.cob.salesforce.utils.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;


@Service
@Transactional
public class UploadPhotoServiceImpl  implements UploadPhotoService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientPhotoImageRepository repository;

    @Override
    public void upload(MultipartFile file) throws IOException {
        repository.save(PatientPhotoImage
                .builder()
                .name(file.getName())
                .type(file.getContentType())
                        .patient(patientRepository.findById(22L).get())
                .image(ImageUtility.compressImage(file.getBytes())).build());
    }
}
