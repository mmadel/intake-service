package com.cob.salesforce.entity.intake;

import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.models.intake.insurance.PatientInsurance;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "new_patient_insurance")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Getter
@Setter
public class PatientInsuranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "patient_insurance_data", columnDefinition = "json")
    @Type(type = "jsonb")
    private PatientInsurance patientInsurance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "patient_insurance_type")
    private InsuranceWorkerType patientInsuranceType;
    @Column
    private Long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}