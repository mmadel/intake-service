package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.Agreement;
import com.cob.salesforce.models.AgreementsDTO;
import com.google.gson.Gson;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AgreementsDTOMapper {

    public static Map<String, String> map(String agreementStr, Iterable<Agreement> agreements) {
        Map<String, String> patientAgreement = new LinkedHashMap<>();
        Gson gson = new Gson();
        AgreementsDTO agreementsDTO = gson.fromJson(agreementStr, AgreementsDTO.class);
        agreements.forEach(agreement -> {
            if (agreement.getAgreementName().equals("ReleaseInformation"))
                patientAgreement.put(agreement.getAgreementName(), agreement.getAgreementText());
            if (agreement.getAgreementName().equals("FinancialResponsibility"))
                patientAgreement.put(agreement.getAgreementName(), agreement.getAgreementText());
            if (agreement.getAgreementName().equals("FinancialAgreement"))
                patientAgreement.put(agreement.getAgreementName(), agreement.getAgreementText());
            if (agreement.getAgreementName().equals("Insurance"))
                patientAgreement.put(agreement.getAgreementName(), agreement.getAgreementText());
            if (agreementsDTO.getAcceptHIPAAAgreements() && agreement.getAgreementName().equals("HIPAAAcknowledgement"))
                patientAgreement.put(agreement.getAgreementName(), agreement.getAgreementText());
            if (agreementsDTO.getAcceptCuppingAgreements() && agreement.getAgreementName().equals("Cupping"))
                patientAgreement.put(agreement.getAgreementName(), agreement.getAgreementText());
            if (agreementsDTO.getAcceptPelvicAgreements() && agreement.getAgreementName().equals("Pelvic"))
                patientAgreement.put(agreement.getAgreementName(), agreement.getAgreementText());
            if (agreementsDTO.getAcceptPhotoVideoAgreements() && agreement.getAgreementName().equals("PhotoVideo"))
                patientAgreement.put(agreement.getAgreementName(), agreement.getAgreementText());
        });
        return patientAgreement;
    }
}
