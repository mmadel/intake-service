package com.cob.salesforce.services;

import com.cob.salesforce.entity.PatientSignatureEntity;
import com.cob.salesforce.models.PatientSignatureDTO;
import com.cob.salesforce.repositories.PatientSignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientSignatureService {

    @Autowired
    PatientSignatureRepository repository;

    public void upload(PatientSignatureDTO model) {
        PatientSignatureEntity entity = new PatientSignatureEntity();
        String base64Image = model.getSignature().split(",")[1];

        entity.setSignature(javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image));
        entity.setPatientId(model.getPatientId());
        repository.save(entity);
    }

    public PatientSignatureDTO get(Long patientId) {
        PatientSignatureEntity ee = repository.findById(patientId).get();
        System.out.println(javax.xml.bind.DatatypeConverter.printBase64Binary(ee.getSignature()));
        PatientSignatureDTO model = new PatientSignatureDTO();
        model.setSignature(javax.xml.bind.DatatypeConverter.printBase64Binary(ee.getSignature()));
        return model;
    }
}
