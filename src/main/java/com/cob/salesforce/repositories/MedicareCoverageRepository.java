package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.MedicareCoverage;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MedicareCoverageRepository extends PagingAndSortingRepository<MedicareCoverage, Long>{}
