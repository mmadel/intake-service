package com.cob.salesforce.services.patient.reports;

import com.cob.salesforce.mappers.PatientContainerMapper;
import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.models.reporting.PatientSearchCriteria;
import com.cob.salesforce.models.reporting.PatientSearchResult;
import com.cob.salesforce.repositories.patient.PatientDoctorSourceRepository;
import com.cob.salesforce.repositories.patient.PatientEntitySourceRepository;
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

        List<PatientContainerDTO> patients = null;
        switch (patientSearchCriteria.getType()) {
            case Doctor:
                patients = patientDoctorSourceRepository.findDoctor(patientSearchCriteria.getDoctorName()
                                , patientSearchCriteria.getDoctorNPI()
                                , patientSearchCriteria.getStartDate()
                                , patientSearchCriteria.getEndDate())
                        .stream().map(patientDoctorSource -> {
                            return mapper.map(patientDoctorSource.getPatient());
                        }).collect(Collectors.toList());
                break;
            case Entity:
                patients = patientEntitySourceRepository.findByEntityName(patientSearchCriteria.getEntityNames(),
                                patientSearchCriteria.getStartDate(),
                                patientSearchCriteria.getEndDate())
                        .stream().map(patientEntitySource -> {
                            return mapper.map(patientEntitySource.getPatient());
                        }).collect(Collectors.toList());
                break;
        }

        return PatientSearchResult.builder()
                .resultCount(patients.size())
                .result(patients)
                .build();
    }
}
