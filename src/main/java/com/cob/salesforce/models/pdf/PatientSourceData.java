package com.cob.salesforce.models.pdf;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PatientSourceData {
    private String doctorName;
    private String doctorNPI;

    private String entityName;
}
