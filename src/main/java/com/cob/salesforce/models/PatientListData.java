package com.cob.salesforce.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class PatientListData {
    Integer number_of_records;
    Integer number_of_matching_records;
    List<PatientContainerDTO> records;

}
