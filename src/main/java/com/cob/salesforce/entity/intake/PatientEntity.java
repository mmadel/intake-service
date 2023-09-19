package com.cob.salesforce.entity.intake;

import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.MaritalStatus;
import com.cob.salesforce.models.intake.agreement.PatientAgreement;
import com.cob.salesforce.models.intake.essentials.*;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Column(name = "patient_essential_information", columnDefinition = "json")
    @Type(type = "json")
    PatientEssentialInformation patientEssentialInformation;
    @Column(name = "patient_agreements", columnDefinition = "json")
    @Type(type = "json")
    private PatientAgreement patientAgreement;
    @Column
    private Long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}
