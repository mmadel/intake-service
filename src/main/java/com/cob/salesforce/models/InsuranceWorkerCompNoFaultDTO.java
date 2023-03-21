
package com.cob.salesforce.models;

import com.cob.salesforce.entity.Patient;
import com.cob.salesforce.enums.CaseStatus;
import com.cob.salesforce.enums.WorkerStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Setter
@Getter
public class InsuranceWorkerCompNoFaultDTO {
    private String injuryType;
    private Long accidentDate;


    private String workerStatus;


    private String address;


    private String fax;


    private String insuranceName;

    private BigInteger claimNumber;

    private String adjusterName;

    private String adjusterPhone;

    private String attorneyName;


    private String attorneyPhone;


    private String caseStatus;

    public WorkerStatus getWorkerStatusEnum(){
        return WorkerStatus.valueOf(workerStatus);
    }
    public CaseStatus getCaseStatusEnum(){
        return CaseStatus.valueOf(caseStatus);
    }
}
