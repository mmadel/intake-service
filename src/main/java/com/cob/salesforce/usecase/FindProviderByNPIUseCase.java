package com.cob.salesforce.usecase;

import com.cob.salesforce.models.integration.BasicProvider;
import com.cob.salesforce.models.integration.nppes.NPPESResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FindProviderByNPIUseCase {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MapNPPESResponse nppesMapper;

    @Value("${nppes.base.url}")
    private String nppesBaseUrl;

    public BasicProvider find(Long npi) {
        String url = nppesBaseUrl + "?version=2.1&number=" + npi;
        NPPESResponse response = restTemplate.getForObject(url, NPPESResponse.class);
        if (response.result_count != 0)
            return nppesMapper.basic_map(response.getResults().get(0));
        else
            return null;
    }
}
