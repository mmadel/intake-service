package com.cob.salesforce.services.patient.admin.insurance;

import com.cob.salesforce.models.admin.insurance.InsuranceCompanyModel;

public interface InsuranceCompanyCreatorService {

    InsuranceCompanyModel create(InsuranceCompanyModel model);
}
