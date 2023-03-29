package com.cob.salesforce.repositories.patient;

import com.cob.salesforce.entity.PatientDoctorSource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientDoctorSourceRepository extends PagingAndSortingRepository<PatientDoctorSource, Long> {

    @Query("SELECT ds FROM patient_doctor_source ds   WHERE (:name is null or ds.name  =:name)" +
            "OR (:npi is null or ds.npi  =:npi) " +
            "AND ((:dateFrom is null or ds.createdAt >= :dateFrom) AND (:dateTo is null or ds.createdAt < :dateTo) )")
    List<PatientDoctorSource> findDoctor(@Param("name") String name
            , @Param("npi") String npi
            , @Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo);
}
