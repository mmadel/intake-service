package com.cob.salesforce.mappers;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.models.PatientContainerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("PatientContainerMapper")
public class PatientContainerMapper {
    @Autowired
    ModelMapper mapper;

    public PatientContainerDTO map(Patient entity) {
        PatientContainerDTO patientContainerDTO = new PatientContainerDTO();
        patientContainerDTO.setBirthDate(entity.getDateOfBirth());
        patientContainerDTO.setEmail(entity.getEmail());
        patientContainerDTO.setGender(entity.getGender() != null ? entity.getGender().label : "");
        patientContainerDTO.setPhoneType(entity.getPhoneType() != null ? entity.getPhoneType().label : "");
        patientContainerDTO.setPhoneNumber(entity.getPhone());
        patientContainerDTO.setIdType(entity.getIdType() != null ? entity.getIdType().label : "");
        patientContainerDTO.setIdEffectiveFrom(entity.getIdEffectiveFrom());
        patientContainerDTO.setIdEffectiveTo(entity.getIdEffectiveTo());
        patientContainerDTO.setPatientId(entity.getPatientId());
        patientContainerDTO.setMaritalStatus(entity.getMaritalStatus() != null ? entity.getMaritalStatus().label : "");
        patientContainerDTO.setEmergencyName(entity.getEmergencyName());
        patientContainerDTO.setEmergencyPhone(entity.getEmergencyPhone());
        patientContainerDTO.setEmploymentStatus(entity.getEmploymentStatus() != null ? entity.getEmploymentStatus().label : "");
        patientContainerDTO.setCreatedAt(entity.getCreatedAt());
        String[] name = entity.getName().split(",");
        patientContainerDTO.setFirstName(name[0]);
        patientContainerDTO.setMiddleName(name[1]);
        patientContainerDTO.setLastName(name[2]);
        String[] address = entity.getAddress().split(",");
        for (String keyVal : address) {
            String[] parts = keyVal.split("=", 2);
            if (parts[0].equals("country"))
                patientContainerDTO.setCountry(parts[1]);
            if (parts[0].equals("first"))
                patientContainerDTO.setFirst(parts[1]);
            if (parts[0].equals("second"))
                patientContainerDTO.setSecond(parts[1]);
            if (parts[0].equals("type"))
                patientContainerDTO.setType(parts[1]);
            if (parts[0].equals("state"))
                patientContainerDTO.setType(parts[1]);
            if (parts[0].equals("province"))
                patientContainerDTO.setType(parts[1]);
            if (parts[0].equals("city"))
                patientContainerDTO.setType(parts[1]);
            if (parts[0].equals("zipCode"))
                patientContainerDTO.setZipCode(parts[1]);
        }
        return patientContainerDTO;
    }
}
