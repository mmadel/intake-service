package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.InsuranceWorkerCompNoFault;
import com.cob.salesforce.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsuranceWorkerInsuranceWorkerCompNoFaultRepository extends PagingAndSortingRepository<InsuranceWorkerCompNoFault, Long> {
     InsuranceWorkerCompNoFault findByPatient_Id(Long Id);

     List<InsuranceWorkerCompNoFault> findByPatientIn(List<Patient> patients);
}
