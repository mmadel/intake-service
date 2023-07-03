package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.InsuranceWorkerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {


    @Query("Select count(p.id)  from patient  p where p.insuranceWorkerType = :insuranceWorkerType")
    public Integer getPatientWorkerType(@Param("insuranceWorkerType") InsuranceWorkerType insuranceWorkerType);

    @Query("Select ps from patient ps" +
            " where (:dateFrom is null or ps.createdAt >= :dateFrom) " +
            "AND (:dateTo is null or ps.createdAt <= :dateTo) " +
            "AND ps.clinic.id = :clinicId")
    List<Patient> getByCreatedDateRangeAndClinicId(@Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo
            , @Param("clinicId") Long clinicId);

    @Query("Select ps from patient ps" +
            " where (:dateFrom is null or ps.createdAt >= :dateFrom) " +
            "AND (:dateTo is null or ps.createdAt <= :dateTo) ")
    List<Patient> getByCreatedDateRange(@Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo);

    @Query("Select ps from patient ps" +
            " where ( ps.createdAt = :sameDate) " +
            "AND ( ps.createdAt = :sameDate)" +
            "AND ps.clinic.id = :clinicId")
    List<Patient> getByCreatedDateAndClinicId(@Param("sameDate") Long sameDate
            , @Param("clinicId") Long clinicId);


    Page<Patient> findByClinicId(Pageable pageable, Long clinicId);

    @Query("Select ps from patient ps" +
            " where ( ps.createdAt >= :start) " +
            "AND ( ps.createdAt <= :end)" +
            "AND ps.clinic.id = :clinicId")
    List<Patient> findInDateRange(@Param("start") Long start, @Param("end") Long end, Long clinicId);

}
