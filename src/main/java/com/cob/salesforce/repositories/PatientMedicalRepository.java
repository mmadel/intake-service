package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientMedical;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientMedicalRepository extends PagingAndSortingRepository<PatientMedical, Long> {
    PatientMedical findByPatient_Id(Long id);

    Long deleteByPatient(Patient patient);
}
