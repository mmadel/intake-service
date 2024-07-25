package com.cob.salesforce.repositories.admin.clinic;

import com.cob.salesforce.entity.admin.InsuranceCompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface InsuranceCompanyRepository extends PagingAndSortingRepository<InsuranceCompanyEntity,Long> {
    @Query("Select ic.id  from InsuranceCompanyEntity ic")
    List<Long> getIds();
    Optional<InsuranceCompanyEntity> findByName(String name);
}
