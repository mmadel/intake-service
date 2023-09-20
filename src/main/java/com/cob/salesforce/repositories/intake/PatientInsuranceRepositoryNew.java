package com.cob.salesforce.repositories.intake;

import com.cob.salesforce.entity.intake.PatientGrantorEntity;
import com.cob.salesforce.entity.intake.PatientInsuranceEntity;
import com.cob.salesforce.entity.intake.PatientMedicalEntity;
import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientInsuranceRepositoryNew extends CrudRepository<PatientInsuranceEntity, Long> {
    Optional<PatientInsuranceEntity> findByPatient_Id(Long id);

    @Query("SELECT count(pi.id) FROM  PatientInsuranceEntity pi  WHERE pi.patient.clinic.id =:clinicId AND pi.patientInsuranceType =:type")
    public Integer findCounterByInsuranceType(@Param("clinicId") Long clinicId, @Param("type") InsuranceWorkerType type);


    @Query("SELECT count(pi.id) FROM  PatientInsuranceEntity pi  WHERE pi.patient.clinic.id =:clinicId AND pi.patientInsuranceType =:type" +
            " AND pi.createdAt >= :startDate" +
            " AND  pi.createdAt <= :endDate")
    public Integer findCounterByInsuranceTypeByDateRange(@Param("startDate") Long startDate, @Param("endDate") Long endDate,
                                                         @Param("clinicId") Long clinicId, @Param("type") InsuranceWorkerType type);
}
