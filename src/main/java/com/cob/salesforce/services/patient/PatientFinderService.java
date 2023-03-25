package com.cob.salesforce.services.patient;

import com.cob.salesforce.models.PatientContainerDTO;
import com.cob.salesforce.models.PatientListData;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientFinderService {
    PatientListData list(Pageable pageable);
}
