package com.cob.salesforce.services.admin.document;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientPhotoImage;

import java.util.List;

public interface PatientDocumentService {

    List<PatientPhotoImage> findIdDocumentByType(Long patientId) ;
    List<PatientPhotoImage> findInsuranceDocumentByType(Long patientId) ;
}
