package com.cob.salesforce.services.admin.insurance;

import com.cob.salesforce.models.admin.insurance.InsuranceCompanyModel;
import com.cob.salesforce.repositories.admin.clinic.InsuranceCompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InsuranceCompanyFinderServiceImpl implements InsuranceCompanyFinderService {
    @Autowired
    InsuranceCompanyRepository repository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<InsuranceCompanyModel> getAll() {
        return StreamSupport
                .stream(Spliterators
                        .spliteratorUnknownSize(repository.findAll().iterator(), 0), false)
                .map(insuranceCompanyEntity -> {
                    return mapper.map(insuranceCompanyEntity, InsuranceCompanyModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public InsuranceCompanyModel getById(Long Id) {
        return mapper.map(repository.findById(Id).get(), InsuranceCompanyModel.class);
    }
}
