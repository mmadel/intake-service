package com.cob.salesforce.models.intake.container.list;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class PatientListContainer {
    Integer number_of_records;
    Integer number_of_matching_records;

    List<PatientRecord> records;
}
