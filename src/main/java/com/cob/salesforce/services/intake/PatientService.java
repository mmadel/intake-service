package com.cob.salesforce.services.intake;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.intake.*;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.grantor.PatientGrantor;
import com.cob.salesforce.models.intake.insurance.PatientInsurance;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.cob.salesforce.models.intake.source.PatientSourceValue;
import com.cob.salesforce.repositories.intake.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientRepositoryNew repository;
    @Autowired
    ModelMapper mapper;

    public Long create(Patient model) {
        PatientEntity created = repository.save(mapper.map(model, PatientEntity.class));
        createPatientSource(created, model.getPatientSource());
        createPatientMedical(created, model.getPatientMedical());
        createPatientInsurance(created, model.getPatientInsurance());
        if (model.getPatientGrantor() != null)
            createPatientGrantor(created, model.getPatientGrantor());
        return created.getId();
    }

    private void createPatientMedical(PatientEntity created, PatientMedical patientMedical) {
        PatientMedicalRepositoryNew repository = BeanFactory.getBean(PatientMedicalRepositoryNew.class);
        PatientMedicalEntity toBeCreated = mapper.map(patientMedical, PatientMedicalEntity.class);
        toBeCreated.setPatient(created);
        repository.save(toBeCreated);
    }

    private void createPatientSource(PatientEntity created, PatientSource patientSource) {
        PatientSourceRepositoryNew repository = BeanFactory.getBean(PatientSourceRepositoryNew.class);
        PatientSourceEntity toBeCreated = new PatientSourceEntity();
        toBeCreated.setPatient(created);
        toBeCreated.setPatient(created);
        if (patientSource.getDoctorSource() != null) {
            toBeCreated.setPatientSourceType(PatientSourceType.Doctor);
            toBeCreated.setPatientSource(PatientSourceValue.builder()
                    .doctorName(patientSource.getDoctorSource().getDoctorName())
                    .doctorNPI(patientSource.getDoctorSource().getDoctorNPI())
                    .build());
        }
        if (patientSource.getEntitySource() != null) {
            toBeCreated.setPatientSourceType(PatientSourceType.Entity);
            toBeCreated.setPatientSource(PatientSourceValue.builder()
                    .organizationName(patientSource.getEntitySource().getOrganizationName())
                    .build());
        }
        repository.save(toBeCreated);
    }

    private void createPatientInsurance(PatientEntity created, PatientInsurance patientInsurance) {
        PatientInsuranceRepositoryNew repository = BeanFactory.getBean(PatientInsuranceRepositoryNew.class);
        PatientInsuranceEntity toBeCreated = mapper.map(patientInsurance, PatientInsuranceEntity.class);
        toBeCreated.setPatient(created);
        repository.save(toBeCreated);
    }

    private void createPatientGrantor(PatientEntity created, PatientGrantor patientGrantor) {
        PatientGrantorRepositoryNew repository = BeanFactory.getBean(PatientGrantorRepositoryNew.class);
        PatientGrantorEntity toBeCreated = mapper.map(patientGrantor, PatientGrantorEntity.class);
        toBeCreated.setPatient(created);
        repository.save(toBeCreated);
    }
}
