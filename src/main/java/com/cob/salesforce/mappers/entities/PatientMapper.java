package com.cob.salesforce.mappers.entities;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("PatientMapper")
public class PatientMapper {
    @Autowired
    ModelMapper mapper;


    public Patient map(PatientDTO dto) {
        return mapper.map(dto, Patient.class);
    }

    @PostConstruct
    public void init() {
        mapper.createTypeMap(PatientDTO.class, Patient.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getBasicInfo().getBirthDate(), Patient::setDateOfBirth);
                    mapper.map(src -> src.getBasicInfo().getEmail(), Patient::setEmail);
                    mapper.map(src -> src.getBasicInfo().getEmergencyName(), Patient::setEmergencyName);
                    mapper.map(src -> src.getBasicInfo().getEmergencyPhone(), Patient::setEmergencyPhone);
                    mapper.map(src -> src.getBasicInfo().getEmergencyRelation(), Patient::setEmergencyRelation);
                    mapper.map(src -> src.getBasicInfo().getEmploymentStatusEnum(), Patient::setEmploymentStatus);
                    mapper.map(src -> src.getBasicInfo().getEmploymentCompany(), Patient::setEmploymentCompany);
                    mapper.map(src -> src.getBasicInfo().getFullName(), Patient::setName);
                    mapper.map(src -> src.getBasicInfo().getFullName(), Patient::setName);
                    mapper.map(src -> src.getBasicInfo().getGenderEnum(), Patient::setGender);
                    mapper.map(src -> src.getBasicInfo().getIDTypeEnum(), Patient::setIdType);
                    mapper.map(src -> src.getBasicInfo().getPatientId(), Patient::setPatientId);
                    mapper.map(src -> src.getBasicInfo().getIdEffectiveFrom(), Patient::setIdEffectiveFrom);
                    mapper.map(src -> src.getBasicInfo().getIdEffectiveTo(), Patient::setIdEffectiveTo);
                    mapper.map(src -> src.getBasicInfo().getMaritalStatusEnum(), Patient::setMaritalStatus);
                    mapper.map(src -> src.getBasicInfo().getPhoneType(), Patient::setPhoneType);
                    mapper.map(src -> src.getBasicInfo().getPhoneNumber(), Patient::setPhone);
                    mapper.map(src -> src.getAddressInfo().getFullAddress(), Patient::setAddress);
                });
    }
}
