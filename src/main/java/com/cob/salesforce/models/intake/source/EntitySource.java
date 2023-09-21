package com.cob.salesforce.models.intake.source;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class EntitySource  implements Serializable {
     private String organizationName;
}
