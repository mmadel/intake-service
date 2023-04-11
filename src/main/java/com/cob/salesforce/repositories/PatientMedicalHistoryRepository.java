package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.PatientMedicalHistory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientMedicalHistoryRepository extends PagingAndSortingRepository<PatientMedicalHistory,Long> {
    PatientMedicalHistory findByPatient_Id(Long id);
}
