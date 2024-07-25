package com.cob.salesforce.services.admin.clinic;

import com.cob.salesforce.exception.business.ClinicException;
import com.cob.salesforce.models.admin.ClinicModel;

public interface ClinicCreatorService {
    ClinicModel create(ClinicModel model);
    boolean isNameExists(String clinicName) throws ClinicException;

}
