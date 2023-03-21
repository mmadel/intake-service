package com.cob.salesforce.services.patient;

import com.cob.salesforce.dependency.creator.IPatientDependencyCreator;
import com.cob.salesforce.dependency.creator.PatientCreator;
import com.cob.salesforce.dependency.creator.PatientDependencyCreator;
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
    PatientDependencyCreator patientDependencyCreator;

    public PatientDTO create(PatientDTO model) {

        Patient savedEntity = creator.create(model);
        List<IPatientDependencyCreator> dependenciesToBeCreated = patientDependencyCreator.createPatientDependencies(model);

        dependenciesToBeCreated
                .forEach(creator -> creator.create(model, savedEntity));

        creator.update(savedEntity);
        return model;
    }
}
