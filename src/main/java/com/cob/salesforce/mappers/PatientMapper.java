package com.cob.salesforce.mappers;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.models.AddressInfoDTO;
import com.cob.salesforce.models.BasicInfoDTO;
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

    public PatientDTO map(Patient entity) {
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO();
        basicInfoDTO.setBirthDate(entity.getDateOfBirth());
        basicInfoDTO.setEmail(entity.getEmail());
        basicInfoDTO.setGender(entity.getGender().label);
        basicInfoDTO.setPhoneType(entity.getPhoneType().label);
        basicInfoDTO.setPhoneNumber(entity.getPhone());
        basicInfoDTO.setIdType(entity.getIdType().label);
        basicInfoDTO.setIdEffectiveFrom(entity.getIdEffectiveFrom());
        basicInfoDTO.setIdEffectiveTo(entity.getIdEffectiveTo());
        basicInfoDTO.setPatientId(entity.getPatientId());
        basicInfoDTO.setMaritalStatus(entity.getMaritalStatus().label);
        basicInfoDTO.setEmergencyName(entity.getEmergencyName());
        basicInfoDTO.setEmergencyPhone(entity.getEmergencyPhone());
        basicInfoDTO.setEmploymentStatus(entity.getEmploymentStatus().label);
        String[] name = entity.getName().split(",");
        basicInfoDTO.setFirstName(name[0]);
        basicInfoDTO.setMiddleName(name[1]);
        basicInfoDTO.setLastName(name[2]);
        AddressInfoDTO addressInfoDTO = new AddressInfoDTO();
        String[] address = entity.getAddress().split(",");
        for (String keyVal : address) {
            String[] parts = keyVal.split("=", 2);
            if (parts[0].equals("country"))
                addressInfoDTO.setCountry(parts[1]);
            if (parts[0].equals("first"))
                addressInfoDTO.setFirst(parts[1]);
            if (parts[0].equals("second"))
                addressInfoDTO.setSecond(parts[1]);
            if (parts[0].equals("type"))
                addressInfoDTO.setType(parts[1]);
            if (parts[0].equals("zipCode"))
                addressInfoDTO.setZipCode(parts[1]);
        }
        PatientDTO dto = new PatientDTO();
        dto.setBasicInfo(basicInfoDTO);
        dto.setAddressInfo(addressInfoDTO);
        return dto;
    }

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
                    mapper.map(src -> src.getBasicInfo().getEmploymentStatusEnum(), Patient::setEmploymentStatus);
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
