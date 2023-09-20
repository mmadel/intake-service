package com.cob.salesforce.services.dashboard;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.entity.intake.PatientSourceEntity;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.dashboard.DashboardDataContainer;
import com.cob.salesforce.models.dashboard.GenderContainer;
import com.cob.salesforce.models.dashboard.PatientSourceContainer;
import com.cob.salesforce.models.dashboard.WeekCounterContainer;
import com.cob.salesforce.repositories.*;
import com.cob.salesforce.repositories.intake.PatientInsuranceRepositoryNew;
import com.cob.salesforce.repositories.intake.PatientRepositoryNew;
import com.cob.salesforce.repositories.intake.PatientSourceRepositoryNew;
import com.cob.salesforce.services.admin.user.UserFinderService;
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
    UserFinderService userFinderService;

    @Autowired
    PatientRepositoryNew patientRepositoryNew;

    @Autowired
    PatientInsuranceRepositoryNew patientInsuranceRepositoryNew;

    @Autowired
    PatientSourceRepositoryNew patientSourceRepositoryNew;
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
    public DashboardDataContainer getData(Long clinicId, String userId, Long startDate, Long endDate) {
        startDate= startDate == null ?  0 : startDate;
        endDate= endDate == null ?  0 : endDate;
        List<PatientEntity> patients = null;
        if (startDate == 0 && endDate == 0)
            patients = patientRepositoryNew.findByClinicId(null, clinicId).getContent();
        else
            patients = patientRepositoryNew.findInDateRange(startDate, endDate, clinicId);
        totalNumberOfPatients = patients.size();
        getPatientAtWeek(clinicId);
        return DashboardDataContainer.builder()
                .totalNumberOfPatient(totalNumberOfPatients)
                .totalNumberOfCompensationNoFaultPatient(getTotalNumberOfCompensationNoFaultPatient(clinicId, startDate, endDate))
                .totalNumberOfCommercialPatient(getTotalNumberOfCommercialPatient(clinicId, startDate, endDate))
                .genderContainer(getGenderData(clinicId, startDate, endDate))
                .patientSourceContainer(getPatientSourceData(clinicId, startDate, endDate))
                .clinicsData(getClinicsData(userId, startDate, endDate))
                .weekCounterContainer(getPatientAtWeek(clinicId))
                .build();
    }

    private int getTotalNumberOfCompensationNoFaultPatient(Long clinicId, Long startDate, Long endDate) {
        int counter = 0;
        if (startDate == 0 || endDate == 0)
            counter= patientInsuranceRepositoryNew.findCounterByInsuranceType(clinicId, InsuranceWorkerType.Comp_NoFault);
        else
            counter =patientInsuranceRepositoryNew.findCounterByInsuranceTypeByDateRange(startDate, endDate, clinicId, InsuranceWorkerType.Comp_NoFault);
        return counter;
    }

    private int getTotalNumberOfCommercialPatient(Long clinicId, Long startDate, Long endDate) {
        int counter = 0;
        if (startDate == 0 || endDate == 0)
            counter= patientInsuranceRepositoryNew.findCounterByInsuranceType(clinicId, InsuranceWorkerType.Commercial);
        else
            counter= patientInsuranceRepositoryNew.findCounterByInsuranceTypeByDateRange(startDate, endDate, clinicId, InsuranceWorkerType.Commercial);
        return counter;
    }

    private GenderContainer getGenderData(Long clinicId, Long startDate, Long endDate) {
        List<PatientEntity> patients;
        if (startDate == 0 || endDate == 0)
            patients = patientRepositoryNew.findByClinicId(null, clinicId).getContent();
        else
            patients = patientRepositoryNew.findInDateRange(startDate, endDate, clinicId);
        int numberOfMale = patients
                .stream()
                .filter(patient -> patient.getPatientEssentialInformation().getGender().equals(Gender.Male)).collect(Collectors.toList()).size();
        int numberOfFemale = patients
                .stream()
                .filter(patient -> patient.getPatientEssentialInformation().getGender().equals(Gender.Female)).collect(Collectors.toList()).size();
        return GenderContainer
                .builder()
                .malePercentage(calculatePercentage(numberOfMale))
                .maleNumber(numberOfMale)
                .femalePercentage(calculatePercentage(numberOfFemale))
                .femaleNumber(numberOfFemale)
                .build();

    }

    private WeekCounterContainer getPatientAtWeek(Long clinicId) {
        Long[] startEnd = DateUtil.startEndDatePeriod(7);
        List<PatientEntity> patients = patientRepositoryNew.findInDateRange(startEnd[0], startEnd[1], clinicId);
        WeekCounterContainer weekCounterContainer = new WeekCounterContainer();
        patients.stream().forEach(patient -> {
            incrementDaysOfWeek(patient.getCreatedAt(), weekCounterContainer, patients.size());
        });
        return weekCounterContainer;
    }


    private Map getClinicsData(String userId, Long startDate, Long endDate) {
        log.debug("DashboardService-Get Clinics Data {}", userId);
        Map clinicData = new HashMap<String, List<Integer>>();
        double totalPatients = 0;
        if (startDate == 0 && endDate == 0)
            totalPatients = Lists.newArrayList(patientRepositoryNew.findAll().iterator()).size();
        else
            totalPatients = patientRepositoryNew.getByCreatedDateRange(startDate, endDate).size();
        log.debug("DashboardService-Get Clinics Data:totalPatients {}", totalPatients);
        double finalTotalPatients = totalPatients;
        userFinderService.findByUserId(userId).forEach(clinicModel -> {
            double numberOfPatient = 0;
            if (startDate == 0 && endDate == 0)
                numberOfPatient = patientRepositoryNew.findByClinicId(null, clinicModel.getId()).getContent().size();
            else
                numberOfPatient = patientRepositoryNew.findInDateRange(startDate, endDate, clinicModel.getId()).size();
            log.debug("DashboardService-Get Clinics Data:Clinic  {} , numberOfPatient {} ", clinicModel.getName(), numberOfPatient);
            List<Double> numberPercentage = new ArrayList<>();
            numberPercentage.add(Double.valueOf(numberOfPatient));
            double clinicPercentage = calculatePercentage(finalTotalPatients, numberOfPatient);
            log.debug("DashboardService-Get Clinics Data:Clinic  {} , Percentage {} ", clinicModel.getName(), clinicPercentage);
            numberPercentage.add(clinicPercentage);
            clinicData.put(clinicModel.getName(), numberPercentage);
        });

        return clinicData;
    }


    private PatientSourceContainer getPatientSourceData(Long clinicId, Long startDate, Long endDate) {
        int numberOfDoctorSource = 0;
        if (startDate == 0 && endDate == 0)
            numberOfDoctorSource = patientSourceRepositoryNew.findCounterBySourceType(clinicId, PatientSourceType.Doctor);
        else
            numberOfDoctorSource = patientSourceRepositoryNew.findCounterBySourceTypeByDateRange(startDate, endDate, clinicId, PatientSourceType.Doctor);
        AtomicInteger numberOfZocdoc = new AtomicInteger();
        AtomicInteger numberOfTV = new AtomicInteger();
        AtomicInteger numberOfWebsite = new AtomicInteger();
        AtomicInteger numberOfSocialMedia = new AtomicInteger();

        List<PatientSourceEntity> patientSourceEntities = null;
        if (startDate == 0 && endDate == 0)
            patientSourceEntities = patientSourceRepositoryNew.findBySourceType(clinicId, PatientSourceType.Entity);
        else
            patientSourceEntities = patientSourceRepositoryNew.findBySourceTypeByDateRange(startDate, endDate, clinicId, PatientSourceType.Entity);
        patientSourceEntities.stream().forEach(patientEntitySource -> {
            if (patientEntitySource.getPatientSource().getOrganizationName().equals("Zocdoc"))
                numberOfZocdoc.getAndIncrement();
            if (patientEntitySource.getPatientSource().getOrganizationName().equals("TV"))
                numberOfTV.getAndIncrement();
            if (patientEntitySource.getPatientSource().getOrganizationName().equals("website"))
                numberOfWebsite.getAndIncrement();
            if (patientEntitySource.getPatientSource().getOrganizationName().equals("socialmedia"))
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