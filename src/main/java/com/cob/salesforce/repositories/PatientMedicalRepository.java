package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.PatientMedical;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientMedicalRepository extends PagingAndSortingRepository<PatientMedical, Long> {
    PatientMedical findByPatient(Long id);
}