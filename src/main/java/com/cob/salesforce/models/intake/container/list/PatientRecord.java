package com.cob.salesforce.models.intake.container.list;

import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PatientRecord {
    String firstName;
    String middleName;
    String lastName;
    String email;
    String phoneNumber;
    PatientSourceType sourceType;
    InsuranceWorkerType insuranceType;

    Long patientId;
}
