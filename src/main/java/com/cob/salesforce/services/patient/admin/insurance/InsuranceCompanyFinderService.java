package com.cob.salesforce.services.patient.admin.insurance;

import com.cob.salesforce.models.admin.insurance.InsuranceCompanyModel;

import java.util.List;

public interface InsuranceCompanyFinderService {
    List<InsuranceCompanyModel> getAll();

    InsuranceCompanyModel getById(Long Id);
}
