package com.cob.salesforce.dependencies.creator.remover;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.repositories.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Getter
public class PatientRemover {
    @Autowired
    PatientMedicalHistoryRepository patientMedicalHistoryRepository;

    @Autowired
    PatientMedicalRepository patientMedicalRepository;
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientPhotoImageRepository patientPhotoImageRepository;

    private Long patientId;

    public void remove(Long patientId) {
        this.patientId = patientId;
        Patient patient = patientRepository.findById(patientId).get();
        patientMedicalHistoryRepository.deleteByPatient(patient);
        patientMedicalRepository.deleteByPatient(patient);
        patientPhotoImageRepository.deleteByPatient(patient);

        removeInsuranceWorker(patient);

        removePatientSource(patient);

        removePhysicalTherapy(patient);

        patientRepository.delete(patient);
    }

    private void removePhysicalTherapy(Patient patient) {
        if (patient.getPhysicalTherapy() != null
                && patient.getPhysicalTherapy()) {
            PatientPhysicalTherapyRepository patientPhysicalTherapyRepository = BeanFactory
                    .getBean(PatientPhysicalTherapyRepository.class);
            patientPhysicalTherapyRepository.deleteByPatient(patient);
        }

    }

    private void removeInsuranceWorker(Patient patient) {
        switch (patient.getInsuranceWorkerType()) {
            case Commercial:
                InsuranceWorkerCommercialRepository commercialRepository = BeanFactory
                        .getBean(InsuranceWorkerCommercialRepository.class);
                commercialRepository.deleteByPatient(patient);
                break;
            case Comp_NoFault:
                InsuranceWorkerInsuranceWorkerCompNoFaultRepository workerCompNoFaultRepository = BeanFactory
                        .getBean(InsuranceWorkerInsuranceWorkerCompNoFaultRepository.class);
                workerCompNoFaultRepository.deleteByPatient(patient);
                break;
        }
    }

    private void removePatientSource(Patient patient) {
        switch (patient.getPatientSourceType()) {
            case Doctor:
                PatientDoctorSourceRepository doctorSourceRepository = BeanFactory
                        .getBean(PatientDoctorSourceRepository.class);
                doctorSourceRepository.deleteByPatient(patient);
                break;
            case Entity:
                PatientEntitySourceRepository entitySourceRepository = BeanFactory
                        .getBean(PatientEntitySourceRepository.class);
                entitySourceRepository.deleteByPatient(patient);
                break;
        }
    }
}
