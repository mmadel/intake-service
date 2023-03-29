package com.cob.salesforce.services.patient.dashboard;

import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.models.dashboard.DashboardDataContainer;
import com.cob.salesforce.models.dashboard.GenderContainer;
import com.cob.salesforce.repositories.patient.PatientRepository;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {
    int totalNumberOfPatients = 0;
    @Autowired
    PatientRepository patientRepository;

    @Override
    public DashboardDataContainer getData() {
        totalNumberOfPatients = Lists.newArrayList(patientRepository.findAll().iterator()).size();
        return DashboardDataContainer.builder()
                .genderContainer(getGenderData())
                .build();
    }

    private GenderContainer getGenderData() {
        int numberOfMale = patientRepository.getPatientByGender(Gender.Male);
        int numberOfFemale = patientRepository.getPatientByGender(Gender.Female);
        return GenderContainer
                .builder()
                .malePercentage(calculatePercentage(numberOfMale))
                .femalePercentage(calculatePercentage(numberOfFemale))
                .build();

    }

    private int calculatePercentage(int toBeCalculated) {
        return (toBeCalculated * 100) / totalNumberOfPatients;
    }
}
