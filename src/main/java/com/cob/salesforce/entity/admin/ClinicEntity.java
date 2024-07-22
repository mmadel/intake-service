package com.cob.salesforce.entity.admin;

import com.cob.salesforce.models.common.AddressModel;
import com.cob.salesforce.models.common.BasicAddress;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Audited(withModifiedFlag = true)
@AuditTable(value = "AU_CLINIC")
public class ClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "clinic_name")
    private String name;
    @Column(name = "address")
    private String address;

    @Type(type = "jsonb")
    @Column(name = "clinic_address", columnDefinition = "json")
    private BasicAddress clinicAddress;

    @Column(name = "clinic_status")
    private Boolean status;

    @Column
    private Long createdAt;
    @Column
    private Long updatedAt;
    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
    @PreUpdate
    private void beforeUpdate() {
        updatedAt = new Date().getTime();
    }
}
