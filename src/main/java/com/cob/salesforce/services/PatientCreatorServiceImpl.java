package com.cob.salesforce.services;

import com.cob.salesforce.dependencies.creator.IPatientDependencyCreator;
import com.cob.salesforce.dependencies.creator.PatientCreator;
import com.cob.salesforce.dependencies.creator.PatientDependencyCreator;
import com.cob.salesforce.dependencies.creator.remover.PatientRemover;
import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.models.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PatientCreatorServiceImpl implements PatientCreatorService {

    @Autowired
    PatientCreator creator;

    @Autowired
    PatientRemover remover;

    @Autowired
    PatientDependencyCreator patientDependencyCreator;


    public Long create(PatientDTO model) {

        Patient savedEntity = creator.create(model);
        List<IPatientDependencyCreator> dependenciesToBeCreated = patientDependencyCreator.createPatientDependencies(model);

        dependenciesToBeCreated
                .forEach(creator -> creator.create(model, savedEntity));

        creator.update(savedEntity);
        return savedEntity.getId();
    }

    @Override
    public void delete(Long patientId) {
        remover.remove(patientId);
    }
}
