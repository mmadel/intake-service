package com.cob.salesforce.entity.validation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Setter
@Getter
public class AddressInfoRequiredEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    Boolean type;
    @Column
    Boolean first;
    @Column
    Boolean second;
    @Column
    Boolean country;
    @Column
    Boolean zipCode;
}
