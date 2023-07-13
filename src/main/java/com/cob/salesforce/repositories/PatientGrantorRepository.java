package com.cob.salesforce.repositories;

import com.cob.salesforce.entity.PatientGrantor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientGrantorRepository  extends JpaRepository<PatientGrantor , Long> {

}
