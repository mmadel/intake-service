package com.cob.salesforce.entity.intake;

import com.cob.salesforce.models.intake.source.PatientSource;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "new_patient_source")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
@Getter
@Setter
public class PatientSourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "patient_source_data", columnDefinition = "json")
    @Type(type = "json")
    private PatientSource patientSource;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    @Column
    private Long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}
