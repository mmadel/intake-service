package com.cob.salesforce.services.admin.document;

import com.cob.salesforce.entity.PatientPhotoImage;
import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.entity.intake.PatientGrantorEntity;
import com.cob.salesforce.enums.admin.documents.PatientDocumentType;
import com.cob.salesforce.repositories.PatientPhotoImageRepository;
import com.cob.salesforce.repositories.intake.PatientGrantorRepositoryNew;
import com.cob.salesforce.repositories.intake.PatientRepositoryNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDocumentServiceImpl implements PatientDocumentService {

    @Autowired
    PatientRepositoryNew patientRepositoryNew;

    @Autowired
    PatientPhotoImageRepository patientPhotoImageRepository;
    @Autowired
    PatientGrantorRepositoryNew patientGrantorRepositoryNew;

    @Override
    public List<PatientPhotoImage> findIdDocumentByType(Long patientId) {
        PatientEntity patient = patientRepositoryNew.findById(patientId).get();
        return patientPhotoImageRepository.findByNameContainingAndPatient(PatientDocumentType.ID.label, patient);
    }

    @Override
    public PatientGrantorEntity findGuarantorIdDocument(Long patientId) {
        return  patientGrantorRepositoryNew.findByPatient_Id(patientId).get();
    }


    @Override
    public List<PatientPhotoImage> findInsuranceDocumentByType(Long patientId) {
        PatientEntity patient = patientRepositoryNew.findById(patientId).get();
        return patientPhotoImageRepository.findByNameContainingAndPatient(PatientDocumentType.INSURANCE.label, patient);
    }
}
