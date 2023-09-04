package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.PatientSignatureEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientSignatureRepository extends CrudRepository<PatientSignatureEntity, Long> {
}
