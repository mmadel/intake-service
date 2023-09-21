package com.cob.salesforce.models.intake.source;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientSource implements Serializable {
    private DoctorSource doctorSource;
    private EntitySource entitySource;
}
