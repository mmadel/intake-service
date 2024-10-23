package com.cob.salesforce.entity.admin.trust.device;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
public class TrustedDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "token")
    private String token;
    @Column(name = "clinic_id")
    private Integer clinicId;
    @Column(name = "created_at")
    private Long createdAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TrustDeviceStatus status;
    @Column(name = "mac_address")
    private String macAddress;
    @Column(name = "device_name")
    private String deviceName;

    @PrePersist
    private void setCreatedDate() {
        createdAt = new Date().getTime();
    }
}
