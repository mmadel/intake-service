package com.cob.salesforce.usecase.patient;

import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.repositories.intake.PatientSourceRepositoryNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountPatientsPerMonthBySource {
    @Autowired
    private PatientSourceRepositoryNew patientSourceRepository;

    public List<Long> countDoctorSource(Integer selectedYear) {
        List<Object[]> response = patientSourceRepository.countPatientsPerMonthBySource(PatientSourceType.Doctor, selectedYear);
        return CreateResponse.create(response);
    }

    public List<Long> countEntitySource(Integer selectedYear) {
        List<Object[]> response = patientSourceRepository.countPatientsPerMonthBySource(PatientSourceType.Entity, selectedYear);
        return CreateResponse.create(response);
    }
}

