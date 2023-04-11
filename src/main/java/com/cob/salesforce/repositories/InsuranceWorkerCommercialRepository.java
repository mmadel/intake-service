package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.InsuranceWorkerCommercial;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InsuranceWorkerCommercialRepository extends PagingAndSortingRepository<InsuranceWorkerCommercial, Long> {

    public InsuranceWorkerCommercial findByPatient_Id(Long id);
}
