package com.cob.salesforce.services;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.mappers.dtos.*;
import com.cob.salesforce.mappers.entities.PatientContainerMapper;
import com.cob.salesforce.models.*;
import com.cob.salesforce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientFinderServiceImpl implements PatientFinderService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientMedicalRepository patientMedicalRepository;

    @Autowired
    PatientMedicalHistoryRepository patientMedicalHistoryRepository;

    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    @Qualifier("PatientContainerMapper")
    PatientContainerMapper mapper;

    @Autowired
    PatientSignatureService patientSignatureService;

    @Override
    public PatientListData getPatients(Pageable pageable, Long clinicId) {
        Page<Patient> pages = patientRepository.findByClinicId(pageable, clinicId);
        long total = (pages).getTotalElements();
        List<PatientContainerDTO> records = pages.stream().map(patient -> mapper.map(patient))
                .collect(Collectors.toList());

        return PatientListData.builder()
                .number_of_records(records.size())
                .number_of_matching_records((int) total)
                .records(records)
                .build();
    }

    @Override
    public PatientDTO getPatient(Long patientId) {
        PatientDTO dto = new PatientDTO();
        Patient patient = patientRepository.findById(patientId).get();
        setPatientMetaData(dto, patient);
        dto.setBasicInfo(PatientBasicInfoDTOMapper.map(patient));
        dto.setAddressInfo(AddressInfoDTOMapper.map(patient.getAddress()));
        dto.setMedicalHistoryInformation(getMedicalHistoryInformation(patientId));
        dto.setMedicalQuestionnaireInfo(getMedicalQuestionnaireInfo(patient));
        setPatientInsuranceInfo(dto, patient);
        setAgreements(dto, patient.getAgreement());
        dto.setPatientSignature(patientSignatureService.get(patient.getId()));
        return dto;
    }

    private MedicalHistoryInformationDTO getMedicalHistoryInformation(Long patientId) {
        MedicalHistoryInformationDTO medicalHistoryInformationDTO =
                MedicalHistoryInformationDTOMapper.map(patientMedicalHistoryRepository.findByPatient_Id(patientId));
        return medicalHistoryInformationDTO;
    }

    private MedicalQuestionnaireInfoDTO getMedicalQuestionnaireInfo(Patient patient) {
        MedicalQuestionnaireInfoDTO medicalQuestionnaireInfoDTO =
                MedicalQuestionnaireInfoDTOMapper.map(patientMedicalRepository.findByPatient_Id(patient.getId()));
        setPatientSource(medicalQuestionnaireInfoDTO, patient);
        setPhysicalTherapy(medicalQuestionnaireInfoDTO, patient);
        return medicalQuestionnaireInfoDTO;
    }

    private void setPatientInsuranceInfo(PatientDTO dto, Patient patient) {
        InsuranceQuestionnaireInfo insuranceQuestionnaireInfo = new InsuranceQuestionnaireInfo();
        switch (patient.getInsuranceWorkerType()) {
            case Comp_NoFault:
                InsuranceWorkerInsuranceWorkerCompNoFaultRepository insuranceWorkerInsuranceWorkerCompNoFaultRepository = BeanFactory
                        .getBean(InsuranceWorkerInsuranceWorkerCompNoFaultRepository.class);
                insuranceQuestionnaireInfo.setInsuranceWorkerCompNoFault(InsuranceWorkerCompNoFaultDTOMapper
                        .map(insuranceWorkerInsuranceWorkerCompNoFaultRepository
                                .findByPatient_Id(patient.getId())));

                insuranceQuestionnaireInfo.setIsCompNoFault(true);
                break;

            case Commercial:
                InsuranceWorkerCommercialRepository commercialRepository = BeanFactory
                        .getBean(InsuranceWorkerCommercialRepository.class);
                insuranceQuestionnaireInfo.setInsuranceWorkerCommercial(InsuranceWorkerCommercialDTOMapper
                        .map(commercialRepository
                                .findByPatient_Id(patient.getId())));
                insuranceQuestionnaireInfo.setIsCompNoFault(false);
                break;
        }
        dto.setInsuranceQuestionnaireInfo(insuranceQuestionnaireInfo);
    }


    private void setPatientSource(MedicalQuestionnaireInfoDTO dto, Patient patient) {
        switch (patient.getPatientSourceType()) {
            case Doctor:
                PatientDoctorSourceRepository doctorSourceRepository = BeanFactory
                        .getBean(PatientDoctorSourceRepository.class);
                dto.setRecommendationDoctor(
                        RecommendationDoctorDTOMapper
                                .map(doctorSourceRepository
                                        .findByPatient_Id(patient.getId())));
                break;
            case Entity:
                PatientEntitySourceRepository patientEntitySourceRepository = BeanFactory
                        .getBean(PatientEntitySourceRepository.class);
                dto.setRecommendationEntity(
                        RecommendationEntityDTOMapper
                                .map(patientEntitySourceRepository
                                        .findByPatient_Id(patient.getId())));
                break;
        }
    }

    private void setPhysicalTherapy(MedicalQuestionnaireInfoDTO dto, Patient patient) {
        if (patient.getPhysicalTherapy() != null && patient.getPhysicalTherapy() == true) {
            PatientPhysicalTherapyRepository patientPhysicalTherapyRepository = BeanFactory
                    .getBean(PatientPhysicalTherapyRepository.class);
            dto.setPhysicalTherapy(
                    PhysicalTherapyDTOMapper.
                            map(patientPhysicalTherapyRepository.findByPatient_Id(patient.getId())));
        }

    }

    private void setAgreements(PatientDTO dto, String agreementStr) {
        dto.setPatientAgreement(AgreementsDTOMapper.map(agreementStr, agreementRepository.findAll()));
    }

    private void setPatientMetaData(PatientDTO dto, Patient entity) {
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setClinicId(entity.getClinic().getId());
    }
}
