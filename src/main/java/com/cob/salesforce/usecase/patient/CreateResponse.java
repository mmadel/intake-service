package com.cob.salesforce.usecase.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateResponse {
    public static List<Long> create(List<Object[]> response){
        List<Long> patientCounts = new ArrayList<>(Arrays.asList(new Long[12]));
        // Fill the list with zeros
        for (int i = 0; i < 12; i++) {
            patientCounts.set(i, 0L);
        }

        // Populate the patientCounts list with values from the API response
        for (Object[] entry  : response) {
            int month = (Integer) entry[0];  // Month (1 for Jan, 2 for Feb, etc.)
            long count = (Long) entry[1];  // Count of patients
            patientCounts.set((int) (month - 1), count);
        }
        return patientCounts;
    }
}
