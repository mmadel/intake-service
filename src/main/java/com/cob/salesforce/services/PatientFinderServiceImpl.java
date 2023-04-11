package com.cob.salesforce.services;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientMedicalHistory;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.mappers.PatientContainerMapper;
import com.cob.salesforce.mappers.pdf.InsuranceDataMapper;
import com.cob.salesforce.mappers.pdf.PatientSourceMapper;
import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.models.PatientListData;
import com.cob.salesforce.models.pdf.InsuranceData;
import com.cob.salesforce.models.pdf.MedicalData;
import com.cob.salesforce.models.pdf.PatientData;
import com.cob.salesforce.models.pdf.PatientSourceData;
import com.cob.salesforce.repositories.*;
import com.cob.salesforce.utils.AddressBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientFinderServiceImpl implements PatientFinderService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    @Qualifier("PatientContainerMapper")
    PatientContainerMapper mapper;

    @Override
    public PatientListData list(Pageable pageable) {
        Page<Patient> pages = patientRepository.findAll(pageable);
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
    public PatientData getPDFPatientData(InsuranceWorkerType insuranceWorkerType,
                                         PatientSourceType patientSourceType, Boolean hasPhysicalTherapy, Long patientId) {
        PatientData patientData ;
        InsuranceData insuranceData = getInsuranceData(insuranceWorkerType, patientId);
        PatientSourceData patientSourceData = getPatientSourceData(patientSourceType, patientId);
        MedicalData medicalData = getPatientMedical(patientId);
        patientData = getPersonalData(medicalData.getPatient());
        patientData.setInsuranceData(insuranceData);
        patientData.setPatientSourceData(patientSourceData);
        patientData.setMedicalData(medicalData);
        return patientData;
    }

    private InsuranceData getInsuranceData(InsuranceWorkerType type, Long patientId) {
        InsuranceData data = null;
        switch (type) {
            case Commercial:
                InsuranceWorkerCommercialRepository commercialRepository = BeanFactory.getBean(InsuranceWorkerCommercialRepository.class);
                data = InsuranceDataMapper.map(commercialRepository.findByPatient_Id(patientId));
                break;
            case Comp_NoFault:
                break;
        }
        return data;
    }

    private PatientSourceData getPatientSourceData(PatientSourceType type, Long patientId) {
        PatientSourceData data = null;
        switch (type) {
            case Doctor:
                PatientDoctorSourceRepository doctorSourceRepository = BeanFactory.getBean(PatientDoctorSourceRepository.class);
                data = PatientSourceMapper.map(doctorSourceRepository.findByPatient_Id(patientId));
                break;
            case Entity:
                PatientEntitySourceRepository patientEntitySourceRepository = BeanFactory.getBean(PatientEntitySourceRepository.class);
                data = PatientSourceMapper.map(patientEntitySourceRepository.findByPatient_Id(patientId));
                break;
        }
        return data;
    }

    private MedicalData getPatientMedical(Long patientId) {
        PatientMedicalHistoryRepository patientMedicalHistoryRepository = BeanFactory.getBean(PatientMedicalHistoryRepository.class);
        PatientMedicalHistory source = patientMedicalHistoryRepository.findByPatient_Id(patientId);
        return MedicalData.builder()
                .height(source.getHeight())
                .weight(source.getWeight())
                .patientCondition(Arrays.asList(source.getPatientCondition().split(",")))
                .patient(source.getPatient())
                .build();
    }

    private PatientData getPersonalData(Patient source) {
        return PatientData.builder()
                .firstName(source.getName().split(",")[0])
                .middleName(source.getName().split(",")[1])
                .lastName(source.getName().split(",")[2])
                .gender(source.getGender())
                .phone(source.getPhone())
                .email(source.getEmail())
                .dateOfBirth(source.getDateOfBirth().toString())
                .patientId(source.getPatientId())
                .address(AddressBuilder.build(source.getAddress()))
                .maritalStatus(source.getMaritalStatus())
                .emergencyFirstName(source.getEmergencyName())
                .emergencyMiddleName(source.getEmergencyName())
                .emergencyLastName(source.getEmergencyName())
                .emergencyPhone(source.getEmergencyPhone())
                .build();
    }
}
