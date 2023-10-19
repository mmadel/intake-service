package com.cob.salesforce.models.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DoctorMessage {
    private String name;
    private String npi;
    private String clinicName;
    private String clinicId;
    private Long createdDate;
    private Boolean isPotential;
}
