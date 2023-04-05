package com.cob.salesforce.mappers.pdf;

import com.cob.salesforce.entity.PatientDoctorSource;
import com.cob.salesforce.entity.PatientEntitySource;
import com.cob.salesforce.models.pdf.PatientSourceData;

public class PatientSourceMapper {

    public static PatientSourceData map(PatientDoctorSource source) {
        return PatientSourceData.builder()
                .doctorName(source.getName())
                .doctorNPI(source.getNpi())
                .build();
    }

    public static PatientSourceData map(PatientEntitySource source) {
        return PatientSourceData.builder()
                .entityName(source.getName())
                .build();
    }
}
