package com.cob.salesforce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "agreement_name")
    private String agreementName;

    @Column(name = "agreement_title")
    private String agreementTitle;


    @Column(name = "agreement_text" , length = 1024)
    private String agreementText;

}
