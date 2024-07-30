package com.cob.salesforce.usecase.patient;

import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.repositories.intake.PatientSourceRepositoryNew;
import com.cob.salesforce.services.admin.user.UserFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountPatientsPerMonthBySource {
    @Autowired
    private PatientSourceRepositoryNew patientSourceRepository;
    @Autowired
    UserFinderService finder;
    public List<Long> countDoctorSource(Integer selectedYear) {
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Long> clinics= finder.findByUserId(uuid).stream()
                .map(clinicModel -> clinicModel.getId())
                .collect(Collectors.toList());

        List<Object[]> response = patientSourceRepository.countPatientsPerMonthBySource(PatientSourceType.Doctor, selectedYear,clinics.toArray(Long[]::new));
        return CreateResponse.create(response);
    }

    public List<Long> countEntitySource(Integer selectedYear) {
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Long> clinics= finder.findByUserId(uuid).stream()
                .map(clinicModel -> clinicModel.getId())
                .collect(Collectors.toList());

        List<Object[]> response = patientSourceRepository.countPatientsPerMonthBySource(PatientSourceType.Entity, selectedYear,clinics.toArray(Long[]::new));
        return CreateResponse.create(response);
    }
}

