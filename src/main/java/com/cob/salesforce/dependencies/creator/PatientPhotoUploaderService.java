package com.cob.salesforce.dependencies.creator;

import org.springframework.web.multipart.MultipartFile;

public interface PatientPhotoUploaderService {
    public void upload( MultipartFile[] files  , Long patientId);
}
