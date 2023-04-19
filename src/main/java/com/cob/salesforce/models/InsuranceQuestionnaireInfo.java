
package com.cob.salesforce.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsuranceQuestionnaireInfo {

    private Boolean isCompNoFault;
    private InsuranceWorkerCommercialDTO insuranceWorkerCommercial;

    private InsuranceWorkerCompNoFaultDTO insuranceWorkerCompNoFault;

}
