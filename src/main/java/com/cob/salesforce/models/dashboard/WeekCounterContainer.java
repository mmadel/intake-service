package com.cob.salesforce.models.dashboard;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class WeekCounterContainer {
    AtomicInteger numberOfMonday = new AtomicInteger();
    double numberOfMondayPercentage;
    AtomicInteger numberOfTuesday = new AtomicInteger();
    double numberOfTuesdayPercentage;
    AtomicInteger numberOfWednesday = new AtomicInteger();
    double numberOfWednesdayPercentage;
    AtomicInteger numberOfThursday = new AtomicInteger();
    double numberOfThursdayPercentage;
    AtomicInteger numberOfFriday = new AtomicInteger();
    double numberOfFridayPercentage;
    AtomicInteger numberOfSaturday = new AtomicInteger();
    double numberOfSaturdayPercentage;
    AtomicInteger numberOfSunday = new AtomicInteger();

    double numberOfSundayPercentage;

}
