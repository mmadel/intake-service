package com.cob.salesforce.entity.admin.trust.device;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class DeviceRegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "token")
    private String token;
    @Column(name = "clinic_id")
    private Integer clinicId;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "expires_at")
    private Long expiresAt;
    @Column(name = "is_used")
    private Boolean isUsed;
}
