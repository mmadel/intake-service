package com.cob.salesforce.services.intake;


import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.models.PatientListData;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.grantor.PatientGrantor;
import com.cob.salesforce.models.intake.insurance.PatientInsurance;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.cob.salesforce.repositories.intake.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientFinderServiceNew {
    @Autowired
    PatientRepositoryNew patientRepository;
    @Autowired
    ModelMapper mapper;

    public List<Patient> getPatients(Pageable pageable, Long clinicId) {
        Page<PatientEntity> pages = patientRepository.findByClinicId(pageable, clinicId);
        List<PatientEntity> entities = pages.getContent();
        return entities.stream().map(patientEntity -> {
            Long patientId = patientEntity.getId();
            Patient patient = mapper.map(patientEntity, Patient.class);
            patient.setPatientMedical(getPatientMedical(patientId));
            patient.setPatientInsurance(getPatientInsurance(patientId));
            patient.setPatientSource(getPatientSource(patientId));
            patient.setPatientGrantor(getPatientGuarantor(patientId));
            return patient;
        }).collect(Collectors.toList());
    }

    private PatientMedical getPatientMedical(Long patientId) {
        PatientMedicalRepositoryNew repository = BeanFactory.getBean(PatientMedicalRepositoryNew.class);
        return mapper.map(repository.findByPatient_Id(patientId).get().getPatientMedical(), PatientMedical.class);
    }

    private PatientInsurance getPatientInsurance(Long patientId) {
        PatientInsuranceRepositoryNew repository = BeanFactory.getBean(PatientInsuranceRepositoryNew.class);
        return mapper.map(repository.findByPatient_Id(patientId).get().getPatientInsurance(), PatientInsurance.class);
    }

    private PatientSource getPatientSource(Long patientId) {
        PatientSourceRepositoryNew repository = BeanFactory.getBean(PatientSourceRepositoryNew.class);
        return mapper.map(repository.findByPatient_Id(patientId).get().getPatientSource(), PatientSource.class);
    }

    private PatientGrantor getPatientGuarantor(Long patientId) {
        PatientGrantor patientGrantor = null;
        PatientGrantorRepositoryNew repository = BeanFactory.getBean(PatientGrantorRepositoryNew.class);
        if(!repository.findByPatient_Id(patientId).isEmpty())
            patientGrantor = mapper.map(repository.findByPatient_Id(patientId).get(), PatientGrantor.class);
        return patientGrantor;
    }
}