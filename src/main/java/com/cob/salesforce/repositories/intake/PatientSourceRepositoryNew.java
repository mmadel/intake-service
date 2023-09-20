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
            "AND (:name is null or (JSON_EXTRACT(ds.patientSource, '$.doctorName') = :name))" +
            "AND (:npi is null or (JSON_EXTRACT(ds.patientSource, '$.doctorNPI') = :npi))" +
            "AND ((:dateFrom is null or ds.createdAt >= :dateFrom) AND (:dateTo is null or ds.createdAt < :dateTo) )" +
            "AND ds.patient.clinic.id =:clinicId")
    List<PatientSourceEntity> findByDoctor(@Param("type") PatientSourceType type,
                                           @Param("name") String name,
                                           @Param("npi") String npi
            , @Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo
            , @Param("clinicId") Long clinicId);


    @Query("SELECT ds FROM PatientSourceEntity ds WHERE (ds.patientSourceType  =:type)  " +
            "AND ((JSON_EXTRACT(ds.patientSource, '$.organizationName')) IN (:names))" +
            "AND ((:dateFrom is null or ds.createdAt >= :dateFrom) AND (:dateTo is null or ds.createdAt < :dateTo) )" +
            "AND ds.patient.clinic.id = :clinicId ")
    List<PatientSourceEntity> findByOrganization(@Param("type") PatientSourceType type,
                                           @Param("names") List<String> names
            , @Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo
            , @Param("clinicId") Long clinicId);
}
