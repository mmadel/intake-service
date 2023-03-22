package com.cob.salesforce.repositories.patient;

import com.cob.salesforce.entity.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
}
