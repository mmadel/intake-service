package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.entity.PatientPhotoImage;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientPhotoImageRepository extends PagingAndSortingRepository<PatientPhotoImage, Long> {
    Long deleteByPatient(Patient patient);

}
