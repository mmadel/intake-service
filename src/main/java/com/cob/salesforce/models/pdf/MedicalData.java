package com.cob.salesforce.models.pdf;

import com.cob.salesforce.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class MedicalData {
    private Double height;
    private Double weight;
    private List<String> patientCondition;
    @JsonIgnore
    private Patient patient;
}
