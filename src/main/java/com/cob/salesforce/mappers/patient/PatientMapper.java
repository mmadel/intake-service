package com.cob.salesforce.mappers.patient;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.enums.EmploymentStatus;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.IDType;
import com.cob.salesforce.enums.PhoneType;
import com.cob.salesforce.models.PatientDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PatientMapper {
    @Autowired
    private ModelMapper mapper;

    @PostConstruct
    private void init() {
        this.mapper.createTypeMap(PatientDTO.class, Patient.class)
        .addMappings(mapper -> {
                    mapper.map(src ->src.getBasicInfo().getFullName(), Patient::setName);
                    mapper.map(src -> src.getBasicInfo().getBirthDate(), Patient::setDateOfBirth);
                    mapper.map(src -> src.getBasicInfo().getGenderEnum(), Patient::setGender);
                    mapper.map(src -> src.getBasicInfo().getPhoneTypeEnum(), Patient::setPhone);
                    mapper.map(src -> src.getBasicInfo().getPhoneNumber(), Patient::setPhone);
                    mapper.map(src -> src.getBasicInfo().getIDTypeEnum(), Patient::setIdType);
                    mapper.map(src -> src.getBasicInfo().getPatientId(), Patient::setPatientId);
                    mapper.map(src -> src.getBasicInfo().getIdEffectiveFrom(), Patient::setIdEffectiveFrom);
                    mapper.map(src -> src.getBasicInfo().getIdEffectiveTo(), Patient::setIdEffectiveTo);
                    mapper.map(src -> src.getBasicInfo().getEmail(), Patient::setEmail);
                    mapper.map(src -> src.getBasicInfo().getEmergencyName(), Patient::setEmergencyName);
                    mapper.map(src -> src.getBasicInfo().getEmergencyPhone(), Patient::setEmergencyPhone);
                    mapper.map(src-> src.getBasicInfo().getEmploymentStatusEnum(),Patient::setEmploymentStatus);
                    mapper.map(src->src.getBasicInfo().getMaritalStatusEnum(), Patient::setMaritalStatus);
                    mapper.map(src-> src.getAddressInfo().getFullAddress() , Patient::setAddress);
                }
        );
    }


    public Patient map(PatientDTO sourceModel) {
        Patient targetModel  = mapper.map(sourceModel , Patient.class);
        return targetModel;
    }

}
