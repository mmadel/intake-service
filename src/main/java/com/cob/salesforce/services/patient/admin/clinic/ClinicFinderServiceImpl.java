package com.cob.salesforce.services.patient.admin.clinic;

import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.repositories.patient.admin.clinic.ClinicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClinicFinderServiceImpl implements ClinicFinderService {
    @Autowired
    ClinicRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public List<ClinicModel> getAll() {
        return StreamSupport
                .stream(Spliterators
                        .spliteratorUnknownSize(repository.findAll().iterator(), 0), false)
                .map(clinicEntity -> {
                    return mapper.map(clinicEntity, ClinicModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public ClinicModel getById(Long Id) {
        return mapper.map(repository.findById(Id).get(), ClinicModel.class);
    }


}
