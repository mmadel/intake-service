package com.cob.salesforce.models.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GenderContainer {
    double malePercentage;
    int maleNumber;
    double femalePercentage;
    int femaleNumber;
}
