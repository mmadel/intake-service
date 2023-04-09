package com.cob.salesforce.services.patient.admin.clinic;

import com.cob.salesforce.models.admin.ClinicModel;

import java.util.List;

public interface ClinicCreatorService {
    ClinicModel create(ClinicModel model);
}
