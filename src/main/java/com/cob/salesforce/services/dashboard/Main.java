package com.cob.salesforce.services.dashboard;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class Main {
    public static void main(String[] args){
//        long time = 1682812800000L;
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//        System.out.println(sdf.format(new Date(time)));
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        System.out.println("Your UUID is: " + uuidAsString);
    }
}
