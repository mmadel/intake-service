package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientEntitySource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientEntitySourceRepository extends PagingAndSortingRepository<PatientEntitySource, Long> {

    @Query("SELECT es FROM patient_entity_source es WHERE   es.name IN (:names)" +
            "AND ((:dateFrom is null or es.created >= :dateFrom) AND (:dateTo is null or es.created < :dateTo) )" +
            "AND es.patient.clinic.id = :clinicId ")
    List<PatientEntitySource> findByEntityName(@Param("names") List<String> names
            , @Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo
            , @Param("clinicId") Long clinicId);

    PatientEntitySource findByPatient_Id(Long id);

    List<PatientEntitySource> findByPatientIn(List<Patient> patients);
}
