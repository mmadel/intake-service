package com.cob.salesforce.entity.validation;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.models.intake.fields.*;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Table(name = "patient_fields")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
@Setter
@Getter
public class PatientFieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "essential_information_fields", columnDefinition = "json")
    @Type(type = "json")
    EssentialInformation essentialInformation;

    @Column(name = "address_information_fields", columnDefinition = "json")
    @Type(type = "json")
    AddressInformation addressInformation;

    @Column(name = "medica_iInformation_fields", columnDefinition = "json")
    @Type(type = "json")
    MedicalInformation medicalInformation;

    @Column(name = "medical_history_information_fields", columnDefinition = "json")
    @Type(type = "json")
    MedicalHistoryInformation medicalHistoryInformation;

    @Column(name = "insurance_compensation_information_fields", columnDefinition = "json")
    @Type(type = "json")
    InsuranceCompensationInformation insuranceCompensationInformation;

    @Column(name = "insurance_commercial_information_fields", columnDefinition = "json")
    @Type(type = "json")
    InsuranceCommercialInformation insuranceCommercialInformation;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private ClinicEntity clinic;
}
