
package com.cob.salesforce.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RecommendationDoctorDTO {


    private AddressInfoDTO doctorAddress;

    private String fax;

    private String name;

    private String npi;

}
