package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientMedicalEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientMedicalRepositoryNew extends CrudRepository<PatientMedicalEntity, Long> {

    Optional<PatientMedicalEntity> findByPatient_Id(Long id);
}
