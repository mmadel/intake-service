package com.cob.salesforce.dependency.creator;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.models.AddressInfoDTO;
import com.cob.salesforce.models.BasicInfoDTO;
import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.repositories.patient.PatientRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@Transactional
@Getter
public class PatientCreator {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    PatientRepository patientRepository;



    public Patient create(PatientDTO model) {
        Patient toBeSaved = mapper.map(model, Patient.class);
        return patientRepository.save(toBeSaved);
    }

    public void update(Patient saved) {
        patientRepository.save(saved);
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
