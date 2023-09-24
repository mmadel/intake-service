package com.cob.salesforce.services.admin.document;

import com.cob.salesforce.entity.PatientPhotoImage;
import com.cob.salesforce.entity.intake.PatientGrantorEntity;

import java.util.List;

public interface PatientDocumentService {

    List<PatientPhotoImage> findIdDocumentByType(Long patientId) ;

    PatientGrantorEntity findGuarantorIdDocument(Long patientId) ;
    List<PatientPhotoImage> findInsuranceDocumentByType(Long patientId) ;
}
