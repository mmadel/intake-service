package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientGrantorEntity;
import com.cob.salesforce.entity.intake.PatientInsuranceEntity;
import com.cob.salesforce.entity.intake.PatientMedicalEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientInsuranceRepositoryNew extends CrudRepository<PatientInsuranceEntity, Long> {
    Optional<PatientInsuranceEntity> findByPatient_Id(Long id);
}
