package com.cob.salesforce.services.patient.dashboard;

import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.models.dashboard.DashboardDataContainer;
import com.cob.salesforce.models.dashboard.GenderContainer;
import com.cob.salesforce.models.dashboard.PatientSourceContainer;
import com.cob.salesforce.repositories.patient.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DashboardServiceImpl implements DashboardService {
    int totalNumberOfPatients = 0;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PatientDoctorSourceRepository patientDoctorSourceRepository;
    @Autowired
    PatientEntitySourceRepository patientEntitySourceRepository;

    @Autowired
    InsuranceWorkerInsuranceWorkerCompNoFaultRepository insuranceWorkerInsuranceWorkerCompNoFaultRepository;
    @Autowired
    InsuranceWorkerCommercialRepository insuranceWorkerCommercialRepository;

    @Override
    public DashboardDataContainer getData() {
        totalNumberOfPatients = Lists.newArrayList(patientRepository.findAll().iterator()).size();
        return DashboardDataContainer.builder()
                .totalNumberOfPatient(totalNumberOfPatients)
                .totalNumberOfCompensationNoFaultPatient(getTotalNumberOfCompensationNoFaultPatient())
                .totalNumberOfCommercialPatient(getTotalNumberOfCommercialPatient())
                .genderContainer(getGenderData())
                .patientSourceContainer(getPatientSourceData())
                .build();
    }

    private int getTotalNumberOfCompensationNoFaultPatient() {
        return Lists.newArrayList(insuranceWorkerInsuranceWorkerCompNoFaultRepository.findAll().iterator()).size();
    }

    private int getTotalNumberOfCommercialPatient() {
        return Lists.newArrayList(insuranceWorkerCommercialRepository.findAll().iterator()).size();
    }

    private GenderContainer getGenderData() {
        int numberOfMale = patientRepository.getPatientByGender(Gender.Male);
        int numberOfFemale = patientRepository.getPatientByGender(Gender.Female);
        return GenderContainer
                .builder()
                .malePercentage(calculatePercentage(numberOfMale))
                .maleNumber(numberOfMale)
                .femalePercentage(calculatePercentage(numberOfFemale))
                .femaleNumber(numberOfFemale)
                .build();

    }

    private PatientSourceContainer getPatientSourceData() {
        int numberOfDoctorSource = Lists.newArrayList(patientDoctorSourceRepository.findAll().iterator()).size();
        AtomicInteger numberOfZocdoc = new AtomicInteger();
        AtomicInteger numberOfTV = new AtomicInteger();
        AtomicInteger numberOfWebsite = new AtomicInteger();
        AtomicInteger numberOfSocialMedia = new AtomicInteger();

        List<PatientEntitySource> PatientEntitySource = Lists.newArrayList(patientEntitySourceRepository.findAll().iterator());
        PatientEntitySource.stream().forEach(patientEntitySource -> {
            if (patientEntitySource.getName().equals("Zocdoc"))
                numberOfZocdoc.getAndIncrement();
            if (patientEntitySource.getName().equals("TV"))
                numberOfTV.getAndIncrement();
            if (patientEntitySource.getName().equals("website"))
                numberOfWebsite.getAndIncrement();
            if (patientEntitySource.getName().equals("Social media"))
                numberOfSocialMedia.getAndIncrement();
        });
        return PatientSourceContainer.builder()
                .zocdocNumber(numberOfZocdoc.get())
                .zocdocPercentage(calculatePercentage(numberOfZocdoc.get()))
                .tvNumber(numberOfTV.get())
                .tvPercentage(calculatePercentage(numberOfTV.get()))
                .websiteNumber(numberOfWebsite.get())
                .websitePercentage(calculatePercentage(numberOfWebsite.get()))
                .socialMediaNumber(numberOfSocialMedia.get())
                .socialMediaPercentage(calculatePercentage(numberOfSocialMedia.get()))
                .doctorNumber(numberOfDoctorSource)
                .doctorPercentage(numberOfDoctorSource)
                .build();
    }

    private int calculatePercentage(int toBeCalculated) {
        return (toBeCalculated * 100) / totalNumberOfPatients;
    }
}
