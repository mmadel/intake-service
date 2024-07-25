package com.cob.salesforce.services.admin.clinic;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.exception.business.ClinicException;
import com.cob.salesforce.models.admin.ClinicModel;
import com.cob.salesforce.models.intake.fields.*;
import com.cob.salesforce.repositories.admin.clinic.ClinicRepository;
import com.cob.salesforce.repositories.admin.user.UserRepository;
import com.cob.salesforce.services.admin.validation.PatientValidationFieldService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClinicCreatorServiceImpl implements ClinicCreatorService {
    @Autowired
    ClinicRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    PatientValidationFieldService patientValidationFieldService;

    @Override
    public ClinicModel create(ClinicModel model) {
        ClinicEntity createdEntity = repository.save(mapper.map(model, ClinicEntity.class));
        model.setId(createdEntity.getId());
        return model;
    }

    private PatientField createPatientValidationFields(Long clinicId) {
        PatientField patientField = new PatientField();
        patientField.setClinicId(clinicId);
        patientField.setEssentialInformation(EssentialInformation.builder()
                .email(false)
                .emergencyContact(false)
                .middleName(false)
                .build());
        patientField.setAddressInformation(AddressInformation.builder()
                .second(false)
                .country(false)
                .zipCode(false)
                .build());
        patientField.setMedicalInformation(MedicalInformation.builder()
                .primaryDoctor(false)
                .recommendedDoctorNpi(false)
                .build());
        patientField.setMedicalHistoryInformation(
                MedicalHistoryInformation.builder()
                        .evaluationReason(false)
                        .medicationPrescription(false)
                        .patientCondition(false)
                        .scanningTest(false)
                        .scanningTestValue(false)
                        .metalImplantation(false)
                        .pacemaker(false)
                        .surgeriesList(false).build()
        );
        patientField.setInsuranceCommercialInformation(
                InsuranceCommercialInformation.builder()
                        .memberId(false)
                        .ploicyId(false)
                        .relationship(false)
                        .secondryInsurance(false)
                        .policyHolderFirstName(false)
                        .policyHolderMiddleName(false)
                        .policyHolderLastName(false)
                        .secondryInsuranceCompany(false)
                        .secondryInsuranceMemberId(false)
                        .medicareCoverage(false)
                        .employerFirstName(false)
                        .employerMiddleName(false)
                        .employerLastName(false)
                        .employerPhone(false)
                        .build());
        patientField.setInsuranceCompensationInformation(InsuranceCompensationInformation.builder()
                .relatedInjury(false)
                .accidentDate(false)
                .workerStatus(false)
                .address(false)
                .fax(false)
                .insuranceName(false)
                .claimNumber(false)
                .adjusterName(false)
                .adjusterPhone(false)
                .attorneyName(false)
                .attorneyPhone(false)
                .caseStatus(false)
                .build());
        return patientField;
    }

    @Override
    public boolean isNameExists(String clinicName) throws ClinicException {
        if (repository.findByName(clinicName).isEmpty())
            return false;
        else
            return true;
    }
}
