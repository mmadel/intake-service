package com.cob.salesforce.services.intake;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.intake.PatientSourceEntity;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.grantor.PatientGrantor;
import com.cob.salesforce.models.intake.insurance.PatientInsurance;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.source.DoctorSource;
import com.cob.salesforce.models.intake.source.EntitySource;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.cob.salesforce.repositories.intake.PatientGrantorRepositoryNew;
import com.cob.salesforce.repositories.intake.PatientInsuranceRepositoryNew;
import com.cob.salesforce.repositories.intake.PatientMedicalRepositoryNew;
import com.cob.salesforce.repositories.intake.PatientSourceRepositoryNew;
import org.modelmapper.ModelMapper;

public class PatientDependenciesMapper {

    public static void mapper(Patient patient) {
        Long patientId = patient.getId();
        patient.setPatientMedical(getPatientMedical(patientId));
        patient.setPatientInsurance(getPatientInsurance(patientId));
        patient.setPatientGrantor(getPatientGuarantor(patientId));
        patient.setPatientSource(getPatientSource(patientId));
    }

    public static PatientSourceType mapToSourceType(Long patientId) {
        return getPatientSource(patientId)
                .getDoctorSource() != null ? PatientSourceType.Doctor : PatientSourceType.Entity;
    }

    public static Boolean mapToHasGuarantor(Long patientId) {
        return getPatientGuarantor(patientId) != null ? true : false;
    }

    public static InsuranceWorkerType mapToInsuranceType(Long patientId) {
        return getPatientInsurance(patientId)
                .getPatientCommercialInsurance() != null ? InsuranceWorkerType.Commercial : InsuranceWorkerType.Comp_NoFault;
    }

    private static PatientMedical getPatientMedical(Long patientId) {
        ModelMapper mapper = BeanFactory.getBean(ModelMapper.class);
        PatientMedicalRepositoryNew repository = BeanFactory.getBean(PatientMedicalRepositoryNew.class);
        return mapper.map(repository.findByPatient_Id(patientId).get().getPatientMedical(), PatientMedical.class);
    }

    private static PatientInsurance getPatientInsurance(Long patientId) {
        ModelMapper mapper = BeanFactory.getBean(ModelMapper.class);
        PatientInsuranceRepositoryNew repository = BeanFactory.getBean(PatientInsuranceRepositoryNew.class);
        return mapper.map(repository.findByPatient_Id(patientId).get().getPatientInsurance(), PatientInsurance.class);
    }

    private static PatientSource getPatientSource(Long patientId) {
        PatientSourceRepositoryNew repository = BeanFactory.getBean(PatientSourceRepositoryNew.class);
        PatientSourceEntity patientSource = repository.findByPatient_Id(patientId).get();
        PatientSource model = new PatientSource();
        switch (patientSource.getPatientSourceType()) {
            case Entity:
                EntitySource entitySource = new EntitySource();
                entitySource.setOrganizationName(patientSource.getPatientSource().getOrganizationName());
                model.setEntitySource(entitySource);
                break;
            case Doctor:
                DoctorSource doctorSource = new DoctorSource();
                doctorSource.setDoctorName(patientSource.getPatientSource().getDoctorName());
                doctorSource.setDoctorNPI(patientSource.getPatientSource().getDoctorNPI());
                model.setDoctorSource(doctorSource);
                break;
        }
        return model;
    }

    private static PatientGrantor getPatientGuarantor(Long patientId) {
        ModelMapper mapper = BeanFactory.getBean(ModelMapper.class);
        PatientGrantor patientGrantor = null;
        PatientGrantorRepositoryNew repository = BeanFactory.getBean(PatientGrantorRepositoryNew.class);
        if (!repository.findByPatient_Id(patientId).isEmpty())
            patientGrantor = mapper.map(repository.findByPatient_Id(patientId).get(), PatientGrantor.class);
        return patientGrantor;
    }
}
