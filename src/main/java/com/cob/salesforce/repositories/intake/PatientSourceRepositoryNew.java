package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientSourceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientSourceRepositoryNew extends CrudRepository<PatientSourceEntity, Long> {
    Optional<PatientSourceEntity> findByPatient_Id(Long id);
}
