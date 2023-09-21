package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.validation.PatientFieldEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientFieldsRepository extends CrudRepository<PatientFieldEntity, Long> {

    Optional<PatientFieldEntity> findByClinicId(Long id);
}
