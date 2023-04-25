package com.cob.salesforce.models.dashboard;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
public class WeekCounterContainer {
    AtomicInteger numberOfMonday = new AtomicInteger();
    int numberOfMondayPercentage;
    AtomicInteger numberOfTuesday = new AtomicInteger();
    int numberOfTuesdayPercentage;
    AtomicInteger numberOfWednesday = new AtomicInteger();
    int numberOfWednesdayPercentage;
    AtomicInteger numberOfThursday = new AtomicInteger();
    int numberOfThursdayPercentage;
    AtomicInteger numberOfFriday = new AtomicInteger();
    int numberOfFridayPercentage;
    AtomicInteger numberOfSaturday = new AtomicInteger();
    int numberOfSaturdayPercentage;
    AtomicInteger numberOfSunday = new AtomicInteger();

    int numberOfSundayPercentage;

}
