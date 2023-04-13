package com.cob.salesforce.repositories.admin;

import com.cob.salesforce.entity.validation.PatientFieldsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientValidationFieldRepository extends PagingAndSortingRepository<PatientFieldsEntity, Long> {
}
