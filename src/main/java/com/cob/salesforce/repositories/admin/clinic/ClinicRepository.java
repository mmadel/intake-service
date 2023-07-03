package com.cob.salesforce.repositories.admin.clinic;

import com.cob.salesforce.entity.admin.ClinicEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClinicRepository extends PagingAndSortingRepository<ClinicEntity, Long> {
}
