package com.cob.salesforce.services.patient;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadPhotoService {
    public void upload(MultipartFile file) throws IOException;
}
