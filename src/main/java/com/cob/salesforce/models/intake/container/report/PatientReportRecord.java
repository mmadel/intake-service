package com.cob.salesforce.models.intake.container.report;

import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.PatientSourceType;
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
    private String gender;

    private Long createdAt;

    private PatientSourceType patientSourceType;

    private String doctorName;
    private String doctorNPI;

    private String organizationName;

    private String primaryDoctor;

}
