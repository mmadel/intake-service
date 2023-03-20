package com.cob.salesforce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "medicare_coverage")
@Getter
@Setter
public class MedicareCoverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="employer_name")
    private String employerName;

    @Column(name="employer_phone")
    private String employerPhone;
}
