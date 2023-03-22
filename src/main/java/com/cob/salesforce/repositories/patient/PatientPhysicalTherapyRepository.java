package com.cob.salesforce.repositories.patient;

import com.cob.salesforce.entity.PhysicalTherapy;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientPhysicalTherapyRepository extends PagingAndSortingRepository<PhysicalTherapy,Long> {
}
