package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientDocumentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientDocumentRepositoryNew extends CrudRepository<PatientDocumentEntity, Long> {
}
