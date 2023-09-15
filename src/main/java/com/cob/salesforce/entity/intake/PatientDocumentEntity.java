package com.cob.salesforce.entity.intake;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="new_patient_document_entity")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDocumentEntity {
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;
}
