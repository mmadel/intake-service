package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientPhotoImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientPhotoImageRepository extends PagingAndSortingRepository<PatientPhotoImage, Long> {
    Long deleteByPatient(Patient patient);

    @Query("select pp from PatientPhotoImage pp where pp.patient.id = :patientId")
    List<PatientPhotoImage> findByIDPatient(@Param("patientId")  Long patientId);

    List<PatientPhotoImage> findByNameContainingAndPatient(String documentType, Patient patient);

}
