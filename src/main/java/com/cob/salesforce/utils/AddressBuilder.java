package com.cob.salesforce.utils;

public class AddressBuilder {

    public static String build(String address) {
        StringBuilder result = new StringBuilder();
        String[] addressArray = address.split(",");
        for (String val : addressArray) {
            String[] parts = val.split("=", 2);
            if (parts[0].equals("country"))
                result.append(parts[1]).append(',');
            if (parts[0].equals("first"))
                result.append(parts[1]).append(',');
            if (parts[0].equals("second"))
                result.append(parts[1]).append(',');
            if (parts[0].equals("type"))
                result.append(parts[1]).append(',');
            if (parts[0].equals("state"))
                result.append(parts[1]).append(',');
            if (parts[0].equals("province"))
                result.append(parts[1]).append(',');
            if (parts[0].equals("city"))
                result.append(parts[1]).append(',');
            if (parts[0].equals("zipCode"))
                result.append(parts[1]);
        }
        return result.toString();
    }
}
