package com.cob.salesforce.entity.intake;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.MaritalStatus;
import com.cob.salesforce.models.intake.agreement.PatientAgreement;
import com.cob.salesforce.models.intake.essentials.*;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "new_patient")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Getter
@Setter
@Audited
@AuditTable(value = "AU_PATIENT")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "patient_essential_information", columnDefinition = "json")
    @Type(type = "jsonb")
    PatientEssentialInformation patientEssentialInformation;
    @Column(name = "patient_agreements", columnDefinition = "json")
    @Type(type = "jsonb")
    private PatientAgreement patientAgreement;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    @NotAudited
    private ClinicEntity clinic;
    @Column
    private Long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}
