package com.cob.salesforce.services;

import com.cob.salesforce.dependencies.creator.IPatientDependencyCreator;
import com.cob.salesforce.dependencies.creator.PatientCreator;
import com.cob.salesforce.dependencies.creator.PatientDependencyCreator;
import com.cob.salesforce.dependencies.creator.PatientGrantorCreatorService;
import com.cob.salesforce.dependencies.creator.remover.PatientRemover;
import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.exception.business.PatientException;
import com.cob.salesforce.models.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    PatientGrantorCreatorService patientGrantorCreatorService;

    @Override
    public Long create(PatientDTO model) throws PatientException {
        Patient savedEntity = null;

        savedEntity = creator.create(model);
        List<IPatientDependencyCreator> dependenciesToBeCreated = patientDependencyCreator.createPatientDependencies(model);

        Patient finalSavedEntity = savedEntity;
        dependenciesToBeCreated
                .forEach(creator -> creator.create(model, finalSavedEntity));

        if (model.getPatientGrantor() != null)
            patientGrantorCreatorService.create(savedEntity, model.getPatientGrantor());
        creator.update(savedEntity);
        return savedEntity.getId();
    }

    @Override
    public void delete(Long patientId) {
        remover.remove(patientId);
    }
}
