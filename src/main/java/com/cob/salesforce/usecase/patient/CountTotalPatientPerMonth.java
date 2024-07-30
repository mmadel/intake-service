package com.cob.salesforce.usecase.patient;

import com.cob.salesforce.repositories.intake.PatientRepositoryNew;
import com.cob.salesforce.services.admin.user.UserFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountTotalPatientPerMonth {
    @Autowired
    private PatientRepositoryNew patientRepository;
    @Autowired
    UserFinderService finder;

    public List<Long>  count(Integer selectedYear){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Long> clinics= finder.findByUserId(uuid).stream()
                .map(clinicModel -> clinicModel.getId())
                .collect(Collectors.toList());

        List<Object[]> response= patientRepository.countPatientsPerMonth(selectedYear,clinics.toArray(Long[]::new));
        return CreateResponse.create(response);
    }
}
