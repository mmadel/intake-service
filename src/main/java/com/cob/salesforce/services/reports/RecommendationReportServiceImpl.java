package com.cob.salesforce.services.reports;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.mappers.entities.PatientContainerMapper;
import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.models.reporting.PatientSearchCriteria;
import com.cob.salesforce.models.reporting.PatientSearchResult;
import com.cob.salesforce.repositories.PatientDoctorSourceRepository;
import com.cob.salesforce.repositories.PatientEntitySourceRepository;
import com.cob.salesforce.repositories.PatientMedicalRepository;
import com.cob.salesforce.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationReportServiceImpl implements RecommendationReportService {
    @Autowired
    PatientEntitySourceRepository patientEntitySourceRepository;
    @Autowired
    PatientDoctorSourceRepository patientDoctorSourceRepository;
    @Autowired
    @Qualifier("PatientContainerMapper")
    PatientContainerMapper mapper;

    @Override
    public PatientSearchResult getReportData(PatientSearchCriteria patientSearchCriteria) {
        List<PatientContainerDTO> patients =
                patientSearchCriteria.getType() != null ?
                        getDataByPatientSourceType(patientSearchCriteria) :
                        getDataByDateRange(patientSearchCriteria.getStartDate(), patientSearchCriteria.getEndDate());

        return PatientSearchResult.builder()
                .resultCount(patients.size())
                .result(patients)
                .build();
    }

    private List<PatientContainerDTO> getDataByPatientSourceType(PatientSearchCriteria patientSearchCriteria) {
        List<PatientContainerDTO> patients = null;
        switch (patientSearchCriteria.getType()) {
            case Doctor:
                PatientDoctorSourceRepository patientDoctorSourceRepository = BeanFactory.getBean(PatientDoctorSourceRepository.class);
                patients = patientDoctorSourceRepository.findDoctor(patientSearchCriteria.getDoctorName()
                                , patientSearchCriteria.getDoctorNPI()
                                , patientSearchCriteria.getStartDate()
                                , patientSearchCriteria.getEndDate()
                                , patientSearchCriteria.getClinicId())
                        .stream().map(patientDoctorSource -> {
                            PatientContainerDTO dto = mapper.map(patientDoctorSource.getPatient());
                            dto.setPatientSourceType(PatientSourceType.Doctor);
                            dto.setDoctorName(patientDoctorSource.getName());
                            dto.setDoctorNPI(patientDoctorSource.getNpi());
                            return dto;
                        }).collect(Collectors.toList());
                break;
            case Entity:
                PatientEntitySourceRepository patientEntitySourceRepository = BeanFactory.getBean(PatientEntitySourceRepository.class);
                PatientMedicalRepository patientMedicalRepository = BeanFactory.getBean(PatientMedicalRepository.class);
                patients = patientEntitySourceRepository.findByEntityName(patientSearchCriteria.getEntityNames(),
                                patientSearchCriteria.getStartDate(),
                                patientSearchCriteria.getEndDate()
                                , patientSearchCriteria.getClinicId())
                        .stream()
                        .map(patientEntitySource -> {
                            PatientContainerDTO dto = mapper.map(patientEntitySource.getPatient());
                            dto.setPatientSourceType(PatientSourceType.Entity);
                            dto.setEntitySourceData(patientEntitySource.getName());
                            dto.setPrimaryDoctor(patientMedicalRepository.findByPatient_Id(patientEntitySource.getPatient().getId()).getPrimaryDoctor());
                            return dto;
                        }).collect(Collectors.toList());
                break;
        }

        return patients;
    }

    private List<PatientContainerDTO> getDataByDateRange(Long dateFrom, Long dateTo) {
        PatientRepository patientRepository = BeanFactory.getBean(PatientRepository.class);
        if (!dateFrom.equals(dateTo))
            return patientRepository.getByCreatedDateRange(dateFrom, dateTo)
                    .stream()
                    .map(patientEntitySource -> mapper.map(patientEntitySource)).collect(Collectors.toList());
        else
            return patientRepository.getByCreatedDate(dateFrom)
                    .stream()
                    .map(patientEntitySource -> mapper.map(patientEntitySource)).collect(Collectors.toList());
    }
}
