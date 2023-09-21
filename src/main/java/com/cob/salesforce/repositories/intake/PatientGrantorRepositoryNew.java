package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientGrantorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientGrantorRepositoryNew extends CrudRepository<PatientGrantorEntity, Long> {
    Optional<PatientGrantorEntity> findByPatient_Id(Long id);
}
