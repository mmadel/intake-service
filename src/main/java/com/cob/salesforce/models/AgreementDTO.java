package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgreementDTO {

    private Long id;

    private String agreementName;

    private String agreementText;
}
