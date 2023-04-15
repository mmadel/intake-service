package com.cob.salesforce.services.dashboard;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.dashboard.DashboardDataContainer;
import com.cob.salesforce.models.dashboard.GenderContainer;
import com.cob.salesforce.models.dashboard.PatientSourceContainer;
import com.cob.salesforce.repositories.*;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    int totalNumberOfPatients = 0;

    @Autowired
    UserRepository userRepository;
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
    public DashboardDataContainer getData(Long clinicId, Long userId) {
        List<Patient> patients = patientRepository.findByClinicId(null, clinicId).getContent();
        totalNumberOfPatients = patients.size();
        return DashboardDataContainer.builder()
                .totalNumberOfPatient(totalNumberOfPatients)
                .totalNumberOfCompensationNoFaultPatient(getTotalNumberOfCompensationNoFaultPatient(patients))
                .totalNumberOfCommercialPatient(getTotalNumberOfCommercialPatient(patients))
                .genderContainer(getGenderData(patients))
                .patientSourceContainer(getPatientSourceData(patients))
                .clinicsData(getClinicsData(userId))
                .build();
    }

    private Map getClinicsData(Long userId) {
        Map clinicData = new HashMap<String, List<Integer>>();
        int totalPatients = Lists.newArrayList(patientEntitySourceRepository.findAll().iterator()).size();
        userRepository.findUserClinics(userId).stream().forEach(clinicEntity -> {
            int numberOfPatient = patientRepository.findByClinicId(null, clinicEntity.getId()).getContent().size();
            List<Integer> numberPercentage = new ArrayList<>();
            numberPercentage.add(numberOfPatient);
            numberPercentage.add(calculatePercentage(totalPatients, numberOfPatient));
            clinicData.put(clinicEntity.getName(), numberPercentage);
        });
        return clinicData;
    }

    private int getTotalNumberOfCompensationNoFaultPatient(List<Patient> patients) {
        return patients
                .stream()
                .filter(patient -> patient.getInsuranceWorkerType().equals(InsuranceWorkerType.Comp_NoFault))
                .collect(Collectors.toList()).size();
    }

    private int getTotalNumberOfCommercialPatient(List<Patient> patients) {
        return patients
                .stream()
                .filter(patient -> patient.getInsuranceWorkerType().equals(InsuranceWorkerType.Commercial))
                .collect(Collectors.toList()).size();
    }

    private GenderContainer getGenderData(List<Patient> patients) {
        int numberOfMale = patients
                .stream()
                .filter(patient -> patient.getGender().equals(Gender.Male)).collect(Collectors.toList()).size();
        int numberOfFemale = patients
                .stream()
                .filter(patient -> patient.getGender().equals(Gender.Female)).collect(Collectors.toList()).size();
        return GenderContainer
                .builder()
                .malePercentage(calculatePercentage(numberOfMale))
                .maleNumber(numberOfMale)
                .femalePercentage(calculatePercentage(numberOfFemale))
                .femaleNumber(numberOfFemale)
                .build();

    }

    private PatientSourceContainer getPatientSourceData(List<Patient> patients) {
        int numberOfDoctorSource = patients.stream()
                .filter(patient -> patient.getPatientSourceType().equals(PatientSourceType.Doctor))
                .collect(Collectors.toList()).size();
        AtomicInteger numberOfZocdoc = new AtomicInteger();
        AtomicInteger numberOfTV = new AtomicInteger();
        AtomicInteger numberOfWebsite = new AtomicInteger();
        AtomicInteger numberOfSocialMedia = new AtomicInteger();

        List<PatientEntitySource> PatientEntitySource = Lists.newArrayList(patientEntitySourceRepository.findByPatientIn(patients).iterator());
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
    private int calculatePercentage(int totalNumberOfPatients,int toBeCalculated) {
        return (toBeCalculated * 100) / totalNumberOfPatients;
    }
}
