package com.cob.salesforce.services.patient.admin.clinic;

import com.cob.salesforce.models.admin.ClinicModel;

import java.util.List;

public interface ClinicFinderService {
    List<ClinicModel> getAll();
    ClinicModel getById(Long Id);
}
