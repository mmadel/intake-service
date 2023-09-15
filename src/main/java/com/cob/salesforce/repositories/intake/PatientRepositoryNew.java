package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepositoryNew  extends CrudRepository<PatientEntity, Long> {
}
