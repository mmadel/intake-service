package com.cob.salesforce.usecase.patient;

import com.cob.salesforce.repositories.intake.PatientRepositoryNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountTotalPatientPerMonth {
    @Autowired
    private PatientRepositoryNew patientRepository;

    public List<Long>  count(Integer selectedYear){
        List<Object[]> response= patientRepository.countPatientsPerMonth(selectedYear);
        return CreateResponse.create(response);
    }
}
