package com.cob.salesforce.services.patient;

import com.cob.salesforce.models.PatientDTO;

import java.util.List;

public interface PatientFinderService {
    List<PatientDTO> list();
}
