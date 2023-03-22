package com.cob.salesforce.repositories.patient;

import com.cob.salesforce.entity.PatientEntitySource;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientEntitySourceRepository extends PagingAndSortingRepository<PatientEntitySource, Long> {
}
