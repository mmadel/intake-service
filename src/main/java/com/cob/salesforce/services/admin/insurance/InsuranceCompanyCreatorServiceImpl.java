package com.cob.salesforce.services.admin.insurance;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.entity.admin.insurance.InsuranceCompanyEntity;
import com.cob.salesforce.models.admin.insurance.InsuranceCompanyModel;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.clinic.InsuranceCompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InsuranceCompanyCreatorServiceImpl implements InsuranceCompanyCreatorService {
    @Autowired
    InsuranceCompanyRepository repository;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public InsuranceCompanyModel create(InsuranceCompanyModel model) {
        List<ClinicEntity> clinicEntityList = new ArrayList<>();
        model.getClinics().forEach(clinicModel ->
        {
            clinicEntityList.add(clinicRepository.findById(clinicModel.getId()).get());
            ;
        });
        InsuranceCompanyEntity created = repository.save(mapper.map(model, InsuranceCompanyEntity.class));
        created.setClinics(clinicEntityList);
        model.setCreatedAt(created.getCreatedAt());
        model.setId(created.getId());
        return model;
    }
}
