package com.cob.salesforce.usecase;

import com.cob.salesforce.models.integration.BasicProvider;
import com.cob.salesforce.models.integration.nppes.NPPESResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindProviderByNPIUseCase {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MapNPPESResponse nppesMapper;

    @Value("${nppes.base.url}")
    private String nppesBaseUrl;

    public List<String> find(Long npi) {
        String url = nppesBaseUrl + "?version=2.1&number=" + npi;
        NPPESResponse response = restTemplate.getForObject(url, NPPESResponse.class);
        if (response.result_count == 0)
            return null;
        else
            return response.results.stream().map(res -> nppesMapper.string_map(res)).collect(Collectors.toList());
    }
}
