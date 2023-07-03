package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.models.AddressInfoDTO;

public class AddressInfoDTOMapper {

    public static AddressInfoDTO map(String addressStr) {
        AddressInfoDTO dto = new AddressInfoDTO();
        String[] address = addressStr.split(",");
        for (String keyVal : address) {
            String[] parts = keyVal.split("=", 2);
            if (parts[0].equals("country"))
                dto.setCountry(parts[1]);
            if (parts[0].equals("first"))
                dto.setFirst(parts[1]);
            if (parts[0].equals("second"))
                dto.setSecond(parts[1]);
            if (parts[0].equals("type"))
                dto.setType(parts[1]);
            if (parts[0].equals("state"))
                dto.setType(parts[1]);
            if (parts[0].equals("province"))
                dto.setType(parts[1]);
            if (parts[0].equals("city"))
                dto.setType(parts[1]);
            if (parts[0].equals("zipCode"))
                dto.setZipCode(parts[1]);
        }
        return dto;
    }
}
