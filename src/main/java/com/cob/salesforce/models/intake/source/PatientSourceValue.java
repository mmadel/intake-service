package com.cob.salesforce.models.intake.source;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientSourceValue implements Serializable {
    String organizationName;
    String doctorName;
    String doctorNPI;
}
