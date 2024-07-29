package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientEntity;
import com.cob.salesforce.models.dashboard.PatientChartContainer;
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

    List<PatientEntity> findByClinic_Id(@Param("clinicId") Long clinicId);

    @Query("SELECT " +
            "EXTRACT(MONTH FROM TO_TIMESTAMP(p.createdAt / 1000)) as month," +
            " COUNT(p) as count " +
            "FROM PatientEntity p " +
            "WHERE EXTRACT(YEAR FROM TO_TIMESTAMP(p.createdAt / 1000)) =:select_year " +
            "GROUP BY " +
            "EXTRACT(YEAR FROM TO_TIMESTAMP(p.createdAt / 1000)) ," +
            "EXTRACT(MONTH FROM TO_TIMESTAMP(p.createdAt / 1000)) " )
    List<Object[]> countPatientsPerMonth(@Param("select_year") Integer select_year);

    @Query("SELECT new com.cob.salesforce.models.dashboard.PatientChartContainer(" +
            "p.clinic.name ," +
            "p.clinic.id ," +
            "EXTRACT(MONTH FROM TO_TIMESTAMP(p.createdAt / 1000)), " +
            "COUNT(p)) " +
            "FROM PatientEntity p " +
            "WHERE EXTRACT(YEAR FROM TO_TIMESTAMP(p.createdAt / 1000)) =:select_year " +
            "AND p.clinic.id in :clinics " +
            "GROUP BY   p.clinic.name,p.clinic.id,EXTRACT(MONTH FROM TO_TIMESTAMP(p.createdAt / 1000)) " +
            "ORDER BY  EXTRACT(MONTH FROM TO_TIMESTAMP(p.createdAt / 1000))")
    List<PatientChartContainer> countPatientsPerMonthGroupedByClinic(@Param("select_year") Integer select_year , @Param("clinics") Long[] clinics );
}
