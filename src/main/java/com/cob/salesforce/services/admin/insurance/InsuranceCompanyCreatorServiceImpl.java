package com.cob.salesforce.services.admin.insurance;

import com.cob.salesforce.entity.admin.insurance.InsuranceCompanyEntity;
import com.cob.salesforce.models.admin.insurance.InsuranceCompanyModel;
import com.cob.salesforce.repositories.admin.clinic.InsuranceCompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InsuranceCompanyCreatorServiceImpl implements InsuranceCompanyCreatorService {
    @Autowired
    InsuranceCompanyRepository repository;

    @Autowired
    ModelMapper mapper;

    @Override
    public InsuranceCompanyModel create(InsuranceCompanyModel model) {
        InsuranceCompanyEntity created = repository.save(mapper.map(model, InsuranceCompanyEntity.class));
        model.setCreatedAt(created.getCreatedAt());
        model.setId(created.getId());
        return model;
    }
}
