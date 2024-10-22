package com.cob.salesforce.entity.admin.trust.device;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class TrustedDeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "token")
    private String token;
    @Column(name = "clinic_id")
    private String clinicId;
    @Column(name = "created_at")
    private Long createdAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TrustDeviceStatus status;
}
