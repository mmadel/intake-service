package com.cob.salesforce.services.intake;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.intake.*;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.messageQ.RabbitMQSender;
import com.cob.salesforce.models.PatientSignatureDTO;
import com.cob.salesforce.models.intake.Patient;
import com.cob.salesforce.models.intake.grantor.PatientGrantor;
import com.cob.salesforce.models.intake.insurance.PatientInsurance;
import com.cob.salesforce.models.intake.medical.PatientMedical;
import com.cob.salesforce.models.intake.source.DoctorSource;
import com.cob.salesforce.models.intake.source.PatientSource;
import com.cob.salesforce.models.intake.source.PatientSourceValue;
import com.cob.salesforce.models.message.DoctorMessage;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.intake.*;
import com.cob.salesforce.services.PatientSignatureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PatientService {
    @Autowired
    PatientRepositoryNew repository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    PatientSignatureService patientSignatureService;

//    @Autowired
//    RabbitMQSender rabbitMQSender;
    @Transactional
    public Long create(Patient model) {
        PatientEntity toBeCreated = mapper.map(model, PatientEntity.class);
        toBeCreated.setClinic(getClinic(model.getClinicId()));
        PatientEntity created = repository.save(toBeCreated);
        createPatientSource(created, model.getPatientSource());
        createPatientMedical(created, model.getPatientMedical());
        createPatientInsurance(created, model.getPatientInsurance());
        if (model.getPatientGrantor() != null)
            createPatientGrantor(created, model.getPatientGrantor());
//        if (model.getPatientSource().getDoctorSource() != null)
//            pushDoctorData(model.getPatientSource().getDoctorSource(), toBeCreated.getClinic());
        PatientSignatureDTO patientSignature = new PatientSignatureDTO();
        patientSignature.setPatientId(created.getId());
        patientSignature.setSignature(model.getSignature());
        patientSignatureService.upload(patientSignature);
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
        toBeCreated.setPatientInsuranceType(patientInsurance.getPatientCommercialInsurance() != null ? InsuranceWorkerType.Commercial : InsuranceWorkerType.Comp_NoFault);
        repository.save(toBeCreated);
    }

    private void createPatientGrantor(PatientEntity created, PatientGrantor patientGrantor) {
        PatientGrantorRepositoryNew repository = BeanFactory.getBean(PatientGrantorRepositoryNew.class);
        PatientGrantorEntity toBeCreated = mapper.map(patientGrantor, PatientGrantorEntity.class);
        toBeCreated.setPatient(created);
        repository.save(toBeCreated);
    }

    private ClinicEntity getClinic(Long id) {
        return clinicRepository.findById(id).get();
    }

//    private void pushDoctorData(DoctorSource doctorSource, ClinicEntity clinic) {
//        rabbitMQSender.send(DoctorMessage.builder()
//                .name(doctorSource.getDoctorName())
//                .npi(doctorSource.getDoctorNPI())
//                .clinicName(clinic.getName())
//                .clinicId(clinic.getId().toString())
//                .createdDate(new Date().getTime())
//                .isPotential(doctorSource.getIsPotential() == null ? true : false)
//                .build());
//    }
}
