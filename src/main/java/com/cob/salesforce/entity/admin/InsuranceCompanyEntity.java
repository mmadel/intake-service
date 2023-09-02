package com.cob.salesforce.entity.admin;

import com.cob.salesforce.entity.admin.ClinicEntity;
import com.cob.salesforce.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Audited()
@AuditTable(schema = "audit_salesforce",catalog ="audit_salesforce",  value = "AU_INSURANCE_COMPANY")
public class InsuranceCompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "clinic_name")
    private String name;
    @Column(name = "address")
    private String address;

    @ManyToMany
    @JoinTable(name = "clinic_insurance_company", joinColumns = @JoinColumn(name = "clinic_id"), inverseJoinColumns = @JoinColumn(name = "insurance_company_id"))
    private List<ClinicEntity> clinics = new ArrayList<>();
}
