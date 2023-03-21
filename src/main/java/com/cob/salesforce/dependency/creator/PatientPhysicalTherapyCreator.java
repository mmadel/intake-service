package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PhysicalTherapy;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientPhysicalTherapyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientPhysicalTherapyCreator implements IPatientDependencyCreator {

    @Autowired
    PatientPhysicalTherapyRepository repository;
    @Autowired
    ModelMapper mapper;

    @Override
    public void create(PatientDTO dto, Patient entity) {
        PhysicalTherapy toBeSaved = mapper.map(dto, PhysicalTherapy.class);
        toBeSaved.setPatient(entity);
        repository.save(toBeSaved);
        entity.setPhysicalTherapy(true);
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(PatientDTO.class, PhysicalTherapy.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getPhysicalTherapy().getLocation(), PhysicalTherapy::setLocation);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getPhysicalTherapy().getNumberOfVisit(), PhysicalTherapy::setNumberOfVisit);
                });
    }

}
