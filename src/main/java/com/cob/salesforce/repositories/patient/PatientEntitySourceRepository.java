package com.cob.salesforce.repositories.patient;

import com.cob.salesforce.entity.PatientEntitySource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientEntitySourceRepository extends PagingAndSortingRepository<PatientEntitySource, Long> {

    @Query("SELECT es FROM patient_entity_source es WHERE   es.name IN (:names)" +
            "AND ((:dateFrom is null or es.created >= :dateFrom) AND (:dateTo is null or es.created < :dateTo) )")
    List<PatientEntitySource> findByEntityName(@Param("names") List<String> names
            , @Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo);
}
