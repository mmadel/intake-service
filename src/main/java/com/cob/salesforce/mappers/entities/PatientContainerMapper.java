package com.cob.salesforce.mappers.entities;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.mappers.dtos.AddressInfoDTOMapper;
import com.cob.salesforce.models.AddressInfoDTO;
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
        patientContainerDTO.setTableId(entity.getId());
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
        patientContainerDTO.setEmergencyRelation(entity.getEmergencyRelation());
        patientContainerDTO.setEmploymentStatus(entity.getEmploymentStatus() != null ? entity.getEmploymentStatus().label : "");
        patientContainerDTO.setEmploymentCompany(entity.getEmploymentCompany());
        patientContainerDTO.setInsuranceWorkerType(entity.getInsuranceWorkerType());
        patientContainerDTO.setPatientSourceType(entity.getPatientSourceType());
        patientContainerDTO.setHasPhysicalTherapy(entity.getPhysicalTherapy());
        patientContainerDTO.setCreatedAt(entity.getCreatedAt());
        String[] name = entity.getName().split(",");
        patientContainerDTO.setFirstName(name[0]);
        patientContainerDTO.setMiddleName(name[1]);
        patientContainerDTO.setLastName(name[2]);
        AddressInfoDTO dto = AddressInfoDTOMapper.map(entity.getAddress());
        patientContainerDTO.setCountry(dto.getCountry());
        patientContainerDTO.setFirst(dto.getFirst());
        patientContainerDTO.setSecond(dto.getSecond());
        patientContainerDTO.setType(dto.getType());
        patientContainerDTO.setState(dto.getState());
        patientContainerDTO.setProvince(dto.getProvince());
        patientContainerDTO.setCity(dto.getCity());
        patientContainerDTO.setZipCode(dto.getZipCode());
        return patientContainerDTO;
    }
}
