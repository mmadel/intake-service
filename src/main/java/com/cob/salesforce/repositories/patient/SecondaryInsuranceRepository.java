package com.cob.salesforce.repositories.patient;

import com.cob.salesforce.entity.SecondaryInsurance;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SecondaryInsuranceRepository extends PagingAndSortingRepository<SecondaryInsurance, Long> {
}
