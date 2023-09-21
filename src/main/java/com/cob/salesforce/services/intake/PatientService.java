package com.cob.salesforce.services.intake;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.intake.*;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.grantor.PatientGrantor;
import com.cob.salesforce.models.intake.insurance.PatientInsurance;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.cob.salesforce.models.intake.source.PatientSourceValue;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.intake.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientRepositoryNew repository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ModelMapper mapper;

    public Long create(Patient model) {
        PatientEntity toBeCreated = mapper.map(model, PatientEntity.class);
        toBeCreated.setClinic(getClinic(model.getClinicId()));
        PatientEntity created = repository.save(toBeCreated);
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
        toBeCreated.setPatientInsuranceType(patientInsurance.getPatientCommercialInsurance() != null? InsuranceWorkerType.Commercial:InsuranceWorkerType.Comp_NoFault);
        repository.save(toBeCreated);
    }

    private void createPatientGrantor(PatientEntity created, PatientGrantor patientGrantor) {
        PatientGrantorRepositoryNew repository = BeanFactory.getBean(PatientGrantorRepositoryNew.class);
        PatientGrantorEntity toBeCreated = mapper.map(patientGrantor, PatientGrantorEntity.class);
        toBeCreated.setPatient(created);
        repository.save(toBeCreated);
    }
    private ClinicEntity getClinic(Long id){
        return clinicRepository.findById(id).get();
    }
}
