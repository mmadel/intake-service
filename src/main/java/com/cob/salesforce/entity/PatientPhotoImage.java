package com.cob.salesforce.entity;

import com.cob.salesforce.entity.intake.PatientEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientPhotoImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;
    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private PatientEntity patient;
}
