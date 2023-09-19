package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.intake.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepositoryNew  extends CrudRepository<PatientEntity, Long> {
    Page<PatientEntity> findByClinicId(Pageable pageable, Long clinicId);
}
