package com.cob.salesforce.dependency.creator;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PatientPhotoUploaderService {

    public void upload(MultipartFile[] files , Long patientId);
}
