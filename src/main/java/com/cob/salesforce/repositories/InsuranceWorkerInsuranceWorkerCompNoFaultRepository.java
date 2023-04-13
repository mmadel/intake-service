package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.InsuranceWorkerCompNoFault;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InsuranceWorkerInsuranceWorkerCompNoFaultRepository extends PagingAndSortingRepository<InsuranceWorkerCompNoFault, Long> {
     InsuranceWorkerCompNoFault findByPatient_Id(Long Id);
}
