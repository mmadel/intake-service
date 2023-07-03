package com.cob.salesforce.models.reporting;

import com.cob.salesforce.enums.PatientSourceType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class PatientSearchCriteria {
    private PatientSourceType type;
    private List<String> entityNames;
    private String  doctorName;
    private String doctorNPI;

    private Long startDate;
    private Long endDate;

    private Long clinicId;
}
