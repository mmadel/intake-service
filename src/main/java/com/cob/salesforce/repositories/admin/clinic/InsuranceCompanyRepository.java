package com.cob.salesforce.repositories.admin.clinic;

import com.cob.salesforce.entity.admin.insurance.InsuranceCompanyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InsuranceCompanyRepository extends PagingAndSortingRepository<InsuranceCompanyEntity,Long> {
}
