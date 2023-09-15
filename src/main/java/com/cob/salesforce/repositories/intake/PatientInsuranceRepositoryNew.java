package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientGrantorEntity;
import com.cob.salesforce.entity.intake.PatientInsuranceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientInsuranceRepositoryNew extends CrudRepository<PatientInsuranceEntity, Long> {
}
