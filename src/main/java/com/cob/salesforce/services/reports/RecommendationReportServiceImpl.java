package com.cob.salesforce.services.reports;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.intake.container.report.PatientReportRecord;
import com.cob.salesforce.models.reporting.PatientSearchCriteria;
import com.cob.salesforce.models.reporting.PatientSearchResult;
import com.cob.salesforce.repositories.intake.PatientRepositoryNew;
import com.cob.salesforce.repositories.intake.PatientSourceRepositoryNew;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationReportServiceImpl implements RecommendationReportService {


    @Override
    public PatientSearchResult getReportData(PatientSearchCriteria patientSearchCriteria) {
        List<PatientReportRecord> patients =
                patientSearchCriteria.getType() != null ?
                        getDataByPatientSourceType(patientSearchCriteria) :
                        getDataByDateRange(patientSearchCriteria.getStartDate(), patientSearchCriteria.getEndDate(), patientSearchCriteria.getClinicId());

        return PatientSearchResult.builder()
                .resultCount(patients.size())
                .result(patients)
                .build();
    }

    private List<PatientReportRecord> getDataByPatientSourceType(PatientSearchCriteria patientSearchCriteria) {
        List<PatientReportRecord> list = null;
        PatientSourceRepositoryNew patientDoctorSourceRepository = BeanFactory.getBean(PatientSourceRepositoryNew.class);
        switch (patientSearchCriteria.getType()) {
            case Doctor:
                return fetchPatientDoctorSource(patientSearchCriteria.getDoctorName(),
                        patientSearchCriteria.getDoctorNPI(),
                        patientSearchCriteria.getStartDate(),
                        patientSearchCriteria.getEndDate(),
                        patientSearchCriteria.getClinicId(),
                        patientDoctorSourceRepository);
            case Entity:
                return fetchPatientEntitySource(patientSearchCriteria.getEntityNames(),
                        patientSearchCriteria.getStartDate(),
                        patientSearchCriteria.getEndDate(),
                        patientSearchCriteria.getClinicId(),
                        patientDoctorSourceRepository);
        }
        return list;
    }

    private List<PatientReportRecord> getDataByDateRange(Long dateFrom, Long dateTo, Long clinicId) {
        PatientRepositoryNew patientRepository = BeanFactory.getBean(PatientRepositoryNew.class);
        if (!dateFrom.equals(dateTo))
            return patientRepository.getByCreatedDateRangeAndClinicId(dateFrom, dateTo, clinicId)
                    .stream()
                    .map(this::constructPatientReportRecord).collect(Collectors.toList());
        else
            return patientRepository.getByCreatedDateAndClinicId(dateFrom, clinicId)
                    .stream()
                    .map(this::constructPatientReportRecord).collect(Collectors.toList());
    }

    private List<PatientReportRecord> fetchPatientDoctorSource(String doctorNameCriteria,
                                                               String doctorNPICriteria, Long startDate, Long endDate,
                                                               Long clinicId,
                                                               PatientSourceRepositoryNew patientDoctorSourceRepository) {
        String doctorName;
        String npi;
        if (doctorNameCriteria == null || doctorNameCriteria.isEmpty())
            doctorName = null;
        else
            doctorName = doctorNameCriteria;

        if (doctorNPICriteria == null || doctorNPICriteria.isEmpty())
            npi = null;
        else
            npi = doctorNPICriteria;
        if ((startDate != null || endDate != null) && (startDate == 0 || endDate == 0)) {
            startDate = null;
            endDate = null;
        }
        return patientDoctorSourceRepository.findByDoctor(
                        PatientSourceType.Doctor,
                        doctorName
                        , npi
                        , startDate
                        , endDate
                        , clinicId)
                .stream().map(patientDoctorSource -> constructPatientReportRecord(patientDoctorSource.getPatient())).collect(Collectors.toList());
    }

    private List<PatientReportRecord> fetchPatientEntitySource(List<String> organizationNameCriteria,
                                                               Long startDate, Long endDate,
                                                               Long clinicId,
                                                               PatientSourceRepositoryNew patientSourceRepository) {
        List<String> organizationName;
        if (organizationNameCriteria == null || organizationNameCriteria.isEmpty())
            organizationName = null;
        else
            organizationName = organizationNameCriteria;
        if ((startDate != null || endDate != null) && (startDate == 0 || endDate == 0)) {
            startDate = null;
            endDate = null;
        }
        return patientSourceRepository.findByOrganization(PatientSourceType.Entity,
                        organizationName,
                        startDate,
                        endDate,
                        clinicId)
                .stream().map(patientSourceEntity -> constructPatientReportRecord(patientSourceEntity.getPatient())).collect(Collectors.toList());
    }

    private PatientReportRecord constructPatientReportRecord(PatientEntity patientEntity) {
        return PatientReportRecord.builder()
                .firstName(patientEntity.getPatientEssentialInformation().getPatientName().getFirstName())
                .middleName(patientEntity.getPatientEssentialInformation().getPatientName().getMiddleName())
                .lastName(patientEntity.getPatientEssentialInformation().getPatientName().getLastName())
                .email(patientEntity.getPatientEssentialInformation().getEmail())
                .phoneNumber(patientEntity.getPatientEssentialInformation().getPatientPhone().getPhone())
                .patientId(patientEntity.getId())
                .build();
    }
}
