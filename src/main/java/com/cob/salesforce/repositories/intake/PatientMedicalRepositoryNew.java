package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientMedicalEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientMedicalRepositoryNew extends CrudRepository<PatientMedicalEntity, Long> {
}
