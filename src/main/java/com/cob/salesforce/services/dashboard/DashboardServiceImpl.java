package com.cob.salesforce.services.dashboard;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.dashboard.DashboardDataContainer;
import com.cob.salesforce.models.dashboard.GenderContainer;
import com.cob.salesforce.models.dashboard.PatientSourceContainer;
import com.cob.salesforce.models.dashboard.WeekCounterContainer;
import com.cob.salesforce.repositories.*;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.utils.DateUtil;
import com.cob.salesforce.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {
    double totalNumberOfPatients = 0;

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
    public DashboardDataContainer getData(Long clinicId, Long userId, Long startDate, Long endDate) {
        List<Patient> patients = null;
        if (startDate == 0 && endDate == 0)
            patients = patientRepository.findByClinicId(null, clinicId).getContent();
        else
            patients = patientRepository.findInDateRange(startDate, endDate, clinicId);
        totalNumberOfPatients = patients.size();
        getPatientAtWeek(clinicId);
        return DashboardDataContainer.builder()
                .totalNumberOfPatient(totalNumberOfPatients)
                .totalNumberOfCompensationNoFaultPatient(getTotalNumberOfCompensationNoFaultPatient(patients))
                .totalNumberOfCommercialPatient(getTotalNumberOfCommercialPatient(patients))
                .genderContainer(getGenderData(patients))
                .patientSourceContainer(getPatientSourceData(patients))
                .clinicsData(getClinicsData(userId, startDate, endDate))
                .weekCounterContainer(getPatientAtWeek(clinicId))
                .build();
    }

    private WeekCounterContainer getPatientAtWeek(Long clinicId) {
        Long[] startEnd = DateUtil.startEndDatePeriod(7);
        List<Patient> patients = patientRepository.findInDateRange(startEnd[0], startEnd[1], clinicId);
        WeekCounterContainer weekCounterContainer = new WeekCounterContainer();
        patients.stream().forEach(patient -> {
            incrementDaysOfWeek(patient.getCreatedAt(), weekCounterContainer, patients.size());
        });
        return weekCounterContainer;
    }

    private Map getClinicsData(Long userId, Long startDate, Long endDate) {
        log.debug("DashboardService-Get Clinics Data {}", userId);
        Map clinicData = new HashMap<String, List<Integer>>();
        double totalPatients = 0;
        if (startDate == 0 && endDate == 0)
            totalPatients = Lists.newArrayList(patientRepository.findAll().iterator()).size();
        else
            totalPatients = patientRepository.getByCreatedDateRange(startDate, endDate).size();
        log.debug("DashboardService-Get Clinics Data:totalPatients {}", totalPatients);
        double finalTotalPatients = totalPatients;
//        userRepository.findUserClinics(userId).stream().forEach(clinicEntity -> {
//            double numberOfPatient = 0;
//            if (startDate == 0 && endDate == 0)
//                numberOfPatient = patientRepository.findByClinicId(null, clinicEntity.getId()).getContent().size();
//            else
//                numberOfPatient = patientRepository.findInDateRange(startDate, endDate, clinicEntity.getId()).size();
//            log.debug("DashboardService-Get Clinics Data:Clinic  {} , numberOfPatient {} ", clinicEntity.getName(), numberOfPatient);
//            List<Double> numberPercentage = new ArrayList<>();
//            numberPercentage.add(Double.valueOf(numberOfPatient));
//            double clinicPercentage = calculatePercentage(finalTotalPatients, numberOfPatient);
//            log.debug("DashboardService-Get Clinics Data:Clinic  {} , Percentage {} ", clinicEntity.getName(), clinicPercentage);
//            numberPercentage.add(clinicPercentage);
//            clinicData.put(clinicEntity.getName(), numberPercentage);
//        });
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
            if (patientEntitySource.getName().equals("socialmedia"))
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
                .doctorPercentage(calculatePercentage(numberOfDoctorSource))
                .build();
    }

    private double calculatePercentage(double toBeCalculated) {

        return totalNumberOfPatients == 0 ? 0 : NumberUtil.round((toBeCalculated * 100) / totalNumberOfPatients, 2);
    }

    private double calculatePercentagePerWeek(double toBeCalculated, double totalNumberOfPatients) {
        return totalNumberOfPatients == 0 ? 0 : NumberUtil.round((toBeCalculated * 100) / totalNumberOfPatients, 2);
    }

    private double calculatePercentage(double totalNumberOfPatients, double toBeCalculated) {
        return totalNumberOfPatients == 0 ? 0 : NumberUtil.round((toBeCalculated * 100) / totalNumberOfPatients, 2);
    }

    private void incrementDaysOfWeek(Long createdDate, WeekCounterContainer weekCounterContainer, int totalNumberOfPatients) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.setTime(new Date(createdDate));
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            weekCounterContainer.getNumberOfMonday().getAndIncrement();
            weekCounterContainer.setNumberOfMondayPercentage(calculatePercentagePerWeek(weekCounterContainer.getNumberOfMonday().get(), totalNumberOfPatients));
        }

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            weekCounterContainer.getNumberOfTuesday().getAndIncrement();
            weekCounterContainer.setNumberOfTuesdayPercentage(calculatePercentagePerWeek(weekCounterContainer.getNumberOfTuesday().get(), totalNumberOfPatients));
        }

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            weekCounterContainer.getNumberOfWednesday().getAndIncrement();
            weekCounterContainer.setNumberOfWednesdayPercentage(calculatePercentagePerWeek(weekCounterContainer.getNumberOfWednesday().get(), totalNumberOfPatients));
        }

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            weekCounterContainer.getNumberOfThursday().getAndIncrement();
            weekCounterContainer.setNumberOfThursdayPercentage(calculatePercentagePerWeek(weekCounterContainer.getNumberOfThursday().get(), totalNumberOfPatients));
        }

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            weekCounterContainer.getNumberOfFriday().getAndIncrement();
            weekCounterContainer.setNumberOfFridayPercentage(calculatePercentagePerWeek(weekCounterContainer.getNumberOfFriday().get(), totalNumberOfPatients));
        }

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            weekCounterContainer.getNumberOfSaturday().getAndIncrement();
            weekCounterContainer.setNumberOfSaturdayPercentage(calculatePercentagePerWeek(weekCounterContainer.getNumberOfSaturday().get(), totalNumberOfPatients));
        }

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            weekCounterContainer.getNumberOfSunday().getAndIncrement();
            weekCounterContainer.setNumberOfSundayPercentage(calculatePercentagePerWeek(weekCounterContainer.getNumberOfSunday().get(), totalNumberOfPatients));
        }
    }
}
