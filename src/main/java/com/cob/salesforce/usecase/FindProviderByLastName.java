package com.cob.salesforce.usecase;

import com.cob.salesforce.models.integration.nppes.NPPESResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindProviderByLastName {
    @Value("${nppes.base.url}")
    private String nppesBaseUrl;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MapNPPESResponse nppesMapper;

    public List<String> find(String lastName){
        String url = "";
        url = nppesBaseUrl + "?version=2.1&last_name=" + lastName.toLowerCase() + "&limit=200";

        NPPESResponse response = restTemplate.getForObject(url, NPPESResponse.class);
        if (response.result_count == 0)
            return null;
        else
            return response.results.stream().map(res -> nppesMapper.string_map(res)).collect(Collectors.toList());

    }
}
