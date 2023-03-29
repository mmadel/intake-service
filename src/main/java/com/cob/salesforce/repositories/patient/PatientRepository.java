package com.cob.salesforce.repositories.patient;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.InsuranceWorkerType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {


    @Query("Select count(p.id)  from patient  p where p.gender = :gender")
    public Integer getPatientByGender(@Param("gender") Gender gender);
    @Query("Select count(p.id)  from patient  p where p.insuranceWorkerType = :insuranceWorkerType")
    public Integer getPatientWorkerType(@Param("insuranceWorkerType") InsuranceWorkerType insuranceWorkerType);
}
