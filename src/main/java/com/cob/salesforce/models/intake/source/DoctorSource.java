package com.cob.salesforce.models.intake.source;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class DoctorSource  implements Serializable {
    private String doctorName;

    private String doctorNPI;

    private Long doctorFax;

    private String doctorAddress;

    private Boolean isPotential;
}
