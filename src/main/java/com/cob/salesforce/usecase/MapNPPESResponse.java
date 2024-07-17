package com.cob.salesforce.usecase;

import com.cob.salesforce.models.common.Address;
import com.cob.salesforce.models.integration.BasicProvider;
import com.cob.salesforce.models.integration.Provider;
import com.cob.salesforce.models.integration.ProviderInfo;
import com.cob.salesforce.models.integration.nppes.NPPESResponse;
import com.cob.salesforce.models.integration.nppes.Result;
import org.springframework.stereotype.Component;

@Component
public class MapNPPESResponse {
    public Provider map(NPPESResponse nppesResponse) {
        Provider provider = new Provider();
        if (nppesResponse.result_count != 0) {
            Result result = nppesResponse.getResults().get(0);
            provider.setFirstName(result.getBasic().getFirst_name());
            provider.setLastName(result.getBasic().getLast_name());
            provider.setPhone(result.getAddresses().get(0).getTelephone_number());
            provider.setNpi(result.getNumber());
            Address address = new Address();
            address.setFirst(result.getAddresses().get(0).address_1);
            address.setSecond(result.getAddresses().get(0).address_2);
            address.setCountry(result.getAddresses().get(0).country_name);
            address.setCity(result.getAddresses().get(0).city);
            address.setState(result.getAddresses().get(0).state);
            address.setZipCode(result.getAddresses().get(0).postal_code);
            provider.setAddress(address);
            ProviderInfo providerInfo = new ProviderInfo();
            providerInfo.setTaxonomyCode(result.getTaxonomies().get(0).code);
            providerInfo.setLicense(result.getTaxonomies().get(0).license);
            provider.setProviderInfo(providerInfo);
        }
        return provider;
    }
    public BasicProvider basic_map(Result result){
        BasicProvider basicProvider = new BasicProvider();
        basicProvider.setNpi(result.getNumber());
        basicProvider.setFirstName(result.getBasic().getFirst_name());
        basicProvider.setLastName(result.getBasic().getLast_name());
        String fullName= result.getBasic().getFirst_name().toLowerCase() + " " + result.getBasic().getLast_name().toLowerCase();
        basicProvider.setFullName(fullName);
        basicProvider.setDisplayName(fullName +" : "+result.getNumber());
        return basicProvider;
    }
    public String string_map(Result result){
        return result.getBasic().getFirst_name().toLowerCase() + " " + result.getBasic().getLast_name().toLowerCase() +":"+result.getNumber();
    }
}
