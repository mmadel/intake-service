package com.cob.salesforce.models.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GenderContainer {
    int malePercentage;
    int maleNumber;
    int femalePercentage;
    int femaleNumber;
}
