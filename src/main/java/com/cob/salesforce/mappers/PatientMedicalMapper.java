package com.cob.salesforce.mappers;

import com.cob.salesforce.entity.PatientDependencyEntity;
import com.cob.salesforce.entity.PatientMedical;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("PatientMedicalMapper")
public class PatientMedicalMapper implements PatientDependencyMapper {
    @Autowired
    ModelMapper mapper;
    @Override
    public PatientDependencyEntity map(PatientDTO dto) {
        return mapper.map(dto, PatientMedical.class);
    }
    @PostConstruct
    public void init() {
        this.mapper.createTypeMap(PatientDTO.class, PatientMedical.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getAppointmentBooking(), PatientMedical::setAppointmentBooking);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getFamilyResultSubmission(), PatientMedical::setFamilyResultSubmission);
                    mapper.map(src -> src.getMedicalQuestionnaireInfo().getPrimaryDoctor(), PatientMedical::setPrimaryDoctor);

                });
    }
}
