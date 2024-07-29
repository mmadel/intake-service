package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientSourceEntity;
import com.cob.salesforce.enums.PatientSourceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientSourceRepositoryNew extends CrudRepository<PatientSourceEntity, Long> {
    Optional<PatientSourceEntity> findByPatient_Id(Long id);

    @Query("SELECT ds FROM PatientSourceEntity ds   WHERE (ds.patientSourceType  =:type)" +
            "AND (:name is null or (FUNCTION('json_extract_path_text',ds.patientSource,'doctorName') = :name))" +
            "AND (:npi is null or (FUNCTION('json_extract_path_text',ds.patientSource,'doctorNPI') = :npi))" +
            "AND ((:dateFrom is null or ds.createdAt >= :dateFrom) AND (:dateTo is null or ds.createdAt < :dateTo) )" +
            "AND ds.patient.clinic.id =:clinicId")
    List<PatientSourceEntity> findByDoctor(@Param("type") PatientSourceType type,
                                           @Param("name") String name,
                                           @Param("npi") String npi
            , @Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo
            , @Param("clinicId") Long clinicId);


    @Query("SELECT ds FROM PatientSourceEntity ds WHERE (ds.patientSourceType  =:type)  " +
            "AND (FUNCTION('json_extract_path_text' ,ds.patientSource, 'organizationName') IN (:names))" +
            "AND ((:dateFrom is null or ds.createdAt >= :dateFrom) AND (:dateTo is null or ds.createdAt < :dateTo) )" +
            "AND ds.patient.clinic.id = :clinicId ")
    List<PatientSourceEntity> findByOrganization(@Param("type") PatientSourceType type,
                                                 @Param("names") List<String> names
            , @Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo
            , @Param("clinicId") Long clinicId);

    @Query("SELECT count(ps.id) FROM  PatientSourceEntity ps  WHERE ps.patient.clinic.id =:clinicId AND ps.patientSourceType =:type")
    public Integer findCounterBySourceType(@Param("clinicId") Long clinicId, @Param("type") PatientSourceType type);


    @Query("SELECT count(ps.id) FROM  PatientSourceEntity ps  WHERE ps.patient.clinic.id =:clinicId AND ps.patientSourceType =:type" +
            " AND ps.createdAt >= :startDate" +
            " AND  ps.createdAt <= :endDate")
    public Integer findCounterBySourceTypeByDateRange(@Param("startDate") Long startDate, @Param("endDate") Long endDate,
                                                         @Param("clinicId") Long clinicId, @Param("type") PatientSourceType type);

    @Query("SELECT ps FROM  PatientSourceEntity ps  WHERE ps.patient.clinic.id =:clinicId AND ps.patientSourceType =:type")
    public List<PatientSourceEntity> findBySourceType(@Param("clinicId") Long clinicId, @Param("type") PatientSourceType type);


    @Query("SELECT ps FROM  PatientSourceEntity ps  WHERE ps.patient.clinic.id =:clinicId AND ps.patientSourceType =:type" +
            " AND ps.createdAt >= :startDate" +
            " AND  ps.createdAt <= :endDate")
    public List<PatientSourceEntity> findBySourceTypeByDateRange(@Param("startDate") Long startDate, @Param("endDate") Long endDate,
                                                      @Param("clinicId") Long clinicId, @Param("type") PatientSourceType type);

    @Query("SELECT  EXTRACT(MONTH FROM TO_TIMESTAMP(ps.patient.createdAt / 1000)) AS month, COUNT(ps.patient) " +
            "FROM PatientSourceEntity ps " +
            "WHERE ps.patientSourceType = :sourceType " +
            "AND EXTRACT(YEAR FROM TO_TIMESTAMP(ps.patient.createdAt / 1000)) =:select_year " +
            "GROUP BY EXTRACT(MONTH FROM TO_TIMESTAMP(ps.patient.createdAt / 1000)) " +
            "ORDER BY  month")
    List<Object[]> countPatientsPerMonthBySource(@Param("sourceType") PatientSourceType sourceType
    ,@Param("select_year") Integer select_year);

}
