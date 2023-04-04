package com.cob.salesforce.services.patient.reports;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.mappers.PatientContainerMapper;
import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.models.reporting.PatientSearchCriteria;
import com.cob.salesforce.models.reporting.PatientSearchResult;
import com.cob.salesforce.repositories.patient.PatientDoctorSourceRepository;
import com.cob.salesforce.repositories.patient.PatientEntitySourceRepository;
import com.cob.salesforce.repositories.patient.PatientRepository;
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
                                , patientSearchCriteria.getEndDate())
                        .stream().map(patientDoctorSource -> {
                            return mapper.map(patientDoctorSource.getPatient());
                        }).collect(Collectors.toList());
                break;
            case Entity:
                PatientEntitySourceRepository patientEntitySourceRepository = BeanFactory.getBean(PatientEntitySourceRepository.class);
                patients = patientEntitySourceRepository.findByEntityName(patientSearchCriteria.getEntityNames(),
                                patientSearchCriteria.getStartDate(),
                                patientSearchCriteria.getEndDate())
                        .stream()
                        .map(patientEntitySource -> {
                            return mapper.map(patientEntitySource.getPatient());
                        }).collect(Collectors.toList());
                break;
        }

        return patients;
    }

    private List<PatientContainerDTO> getDataByDateRange(Long dateFrom, Long dateTo) {
        List<PatientContainerDTO> patients = null;
        PatientRepository patientRepository = BeanFactory.getBean(PatientRepository.class);
        if (!dateFrom.equals(dateTo))
            return patientRepository.getByCreatedDateRange(dateFrom, dateTo)
                    .stream()
                    .map(patientEntitySource -> {
                        return mapper.map(patientEntitySource);
                    }).collect(Collectors.toList());
        else
            return patientRepository.getByCreatedDate(dateFrom)
                    .stream()
                    .map(patientEntitySource -> {
                        return mapper.map(patientEntitySource);
                    }).collect(Collectors.toList());
    }
}
