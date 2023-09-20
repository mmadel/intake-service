package com.cob.salesforce.models.intake.container.report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PatientReportRecord {
    String firstName;
    String middleName;
    String lastName;
    String email;
    String phoneNumber;
    Long patientId;
}
