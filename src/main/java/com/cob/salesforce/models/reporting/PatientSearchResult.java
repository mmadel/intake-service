package com.cob.salesforce.models.reporting;

import com.cob.salesforce.models.PatientContainerDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class PatientSearchResult {
    int resultCount;
    List<PatientContainerDTO> result;
}
