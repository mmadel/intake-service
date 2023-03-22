package com.cob.salesforce.repositories.patient;

import com.cob.salesforce.entity.PatientDoctorSource;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientDoctorSourceRepository extends PagingAndSortingRepository<PatientDoctorSource, Long> {
}
