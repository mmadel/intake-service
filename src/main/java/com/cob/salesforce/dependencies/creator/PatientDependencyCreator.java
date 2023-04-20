package com.cob.salesforce.dependencies.creator;

import com.cob.salesforce.BeanFactory;
import com.cob.salesforce.models.PatientDTO;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Getter
public class PatientDependencyCreator {


    public List<IPatientDependencyCreator> createPatientDependencies(PatientDTO model) {
        List<IPatientDependencyCreator> patientDependencyCreator = new ArrayList<>();
        patientDependencyCreator.add(BeanFactory.getBean(PatientMedicalCreator.class));
        patientDependencyCreator.add(BeanFactory.getBean(PatientMedicalHistoryCreator.class));

        if (model.getMedicalQuestionnaireInfo().getPhysicalTherapyReceiving())
            patientDependencyCreator.add(BeanFactory.getBean(PatientPhysicalTherapyCreator.class));

        if (model.getMedicalQuestionnaireInfo().getIsDoctorRecommended())
            patientDependencyCreator.add(BeanFactory.getBean(PatientDoctorSourceCreator.class));
        else
            patientDependencyCreator.add(BeanFactory.getBean(PatientEntitySourceCreator.class));

        if (model.getInsuranceQuestionnaireInfo().getIsCompNoFault())
            patientDependencyCreator.add(BeanFactory.getBean(PatientCompNoFaultCreator.class));
        else
            patientDependencyCreator.add(BeanFactory.getBean(PatientCommercialCreator.class));

        return patientDependencyCreator;

    }
}
