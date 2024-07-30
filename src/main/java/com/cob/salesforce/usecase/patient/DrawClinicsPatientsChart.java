package com.cob.salesforce.usecase.patient;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.models.dashboard.PatientChartContainer;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.intake.PatientRepositoryNew;
import com.cob.salesforce.services.admin.user.UserFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DrawClinicsPatientsChart {
    @Autowired
    PatientRepositoryNew patientRepositoryNew;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    UserFinderService finder;

    public List<PatientChartContainer> getData(Integer selectedYear) {
         String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Long> clinics= finder.findByUserId(uuid).stream()
                .map(clinicModel -> clinicModel.getId())
                .collect(Collectors.toList());

        List<PatientChartContainer> result = patientRepositoryNew.countPatientsPerMonthGroupedByClinic(selectedYear, clinics.toArray(Long[]::new));
        Set<Long> clinicIdSet = result.stream()
                .map(PatientChartContainer::getClinicId)
                .collect(Collectors.toSet());
        List<Long> notFoundIds = clinics.stream()
                .filter(id -> !clinicIdSet.contains(id))
                .collect(Collectors.toList());

        List<ClinicEntity> hasNoPatients = new ArrayList<>();
        for (int i = 0; i < notFoundIds.size(); i++) {
             hasNoPatients = StreamSupport.stream(clinicRepository.findAllById(notFoundIds).spliterator(), false)
                    .collect(Collectors.toList());
        }
        hasNoPatients.forEach(clinic -> {
            PatientChartContainer patientChartContainer = new PatientChartContainer(clinic.getName(), clinic.getId(), 0, 0);
            result.add(patientChartContainer);
        });
        return result;
    }
}
