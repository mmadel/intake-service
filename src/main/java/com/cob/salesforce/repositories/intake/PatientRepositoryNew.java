package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.intake.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepositoryNew extends CrudRepository<PatientEntity, Long> {
    @Query("Select ps from PatientEntity ps" +
            " where (:dateFrom is null or ps.createdAt >= :dateFrom) " +
            "AND (:dateTo is null or ps.createdAt <= :dateTo) " +
            "AND ps.clinic.id = :clinicId")
    List<PatientEntity> getByCreatedDateRangeAndClinicId(@Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo
            , @Param("clinicId") Long clinicId);


    @Query("Select ps from PatientEntity ps" +
            " where ( ps.createdAt = :sameDate) " +
            "AND ( ps.createdAt = :sameDate)" +
            "AND ps.clinic.id = :clinicId")
    List<PatientEntity> getByCreatedDateAndClinicId(@Param("sameDate") Long sameDate
            , @Param("clinicId") Long clinicId);

    Page<PatientEntity> findByClinicId(Pageable pageable, Long clinicId);

    @Query("Select ps from PatientEntity ps" +
            " where ( ps.createdAt >= :startDate) " +
            "AND ( ps.createdAt <= :endDate)" +
            "AND ps.clinic.id = :clinicId")
    List<PatientEntity> findInDateRange(@Param("startDate") Long startDate, @Param("endDate") Long endDate, Long clinicId);

    @Query("Select ps from PatientEntity ps" +
            " where (:dateFrom is null or ps.createdAt >= :dateFrom) " +
            "AND (:dateTo is null or ps.createdAt <= :dateTo) ")
    List<PatientEntity> getByCreatedDateRange(@Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo);
}
