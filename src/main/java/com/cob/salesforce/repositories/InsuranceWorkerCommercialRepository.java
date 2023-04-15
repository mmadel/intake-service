package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.InsuranceWorkerCommercial;
import com.cob.salesforce.entity.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface InsuranceWorkerCommercialRepository extends PagingAndSortingRepository<InsuranceWorkerCommercial, Long> {

    InsuranceWorkerCommercial findByPatient_Id(Long id);
    List<InsuranceWorkerCommercial> findByPatientIn(List<Patient> patients);
}
