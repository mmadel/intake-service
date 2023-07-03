package com.cob.salesforce.mappers.entities;

import com.cob.salesforce.entity.PatientDependencyEntity;
import com.cob.salesforce.models.PatientDTO;

public interface PatientDependencyMapper {
    PatientDependencyEntity map(PatientDTO model);
}
