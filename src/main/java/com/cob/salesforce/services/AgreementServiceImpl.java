package com.cob.salesforce.services;

import com.cob.salesforce.models.AgreementDTO;
import com.cob.salesforce.repositories.AgreementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    @Autowired
    AgreementRepository repository;
    @Autowired
    ModelMapper mapper;
    @Override
    public List<AgreementDTO> finaAll() {
        List<AgreementDTO> result = new ArrayList<>();
        repository.findAll().forEach(agreement -> {
            AgreementDTO dto = mapper.map(agreement , AgreementDTO.class);
            result.add(dto);
        });
        return result;
    }
}
