package com.cob.salesforce.entity.intake;

import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.MaritalStatus;
import com.cob.salesforce.models.intake.essentials.*;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "new_patient")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
@Getter
@Setter
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "patient_name", columnDefinition = "json")
    @Type(type = "json")
    private PatientName patientName;

    @Column(name = "date_of_birth")
    private Long dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "patient_phone", columnDefinition = "json")
    @Type(type = "json")
    private PatientPhone patientPhone;

    @Column(name = "email")
    private String email;
    @Column(name = "patient_address", columnDefinition = "json")
    @Type(type = "json")
    private PatientAddress patientAddress;
    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "patient_emergency_contact", columnDefinition = "json")
    @Type(type = "json")
    private PatientEmergencyContact patientEmergencyContact;

    @Column(name = "patient_employment", columnDefinition = "json")
    @Type(type = "json")
    private PatientEmployment patientEmployment;
    @Column
    private Long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}
