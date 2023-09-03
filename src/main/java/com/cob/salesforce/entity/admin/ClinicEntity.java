package com.cob.salesforce.entity.admin;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Audited(withModifiedFlag = true)
@AuditTable(schema = "audit_salesforce",catalog ="audit_salesforce",  value = "AU_CLINIC")
public class ClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "clinic_name")
    private String name;
    @Column(name = "address")
    private String address;
}
