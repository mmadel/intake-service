package com.cob.salesforce.models.dashboard;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientChartContainer {
    private String clinicName;
    private Long clinicId;
    private int month;
    private long count;

    public PatientChartContainer(String clinicName, Long clinicId, int month, long count) {
        this.clinicName = clinicName;
        this.clinicId = clinicId;
        this.month = month;
        this.count = count;
    }
}
