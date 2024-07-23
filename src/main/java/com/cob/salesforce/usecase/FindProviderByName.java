package com.cob.salesforce.usecase;

import com.cob.salesforce.models.integration.BasicProvider;
import com.cob.salesforce.models.integration.nppes.NPPESResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindProviderByName {
    @Value("${nppes.base.url}")
    private String nppesBaseUrl;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MapNPPESResponse nppesMapper;

    public List<BasicProvider> find(String name) {
        String url = "";
        String[] fullName = name.split(" ");
        if (fullName.length == 1)
            url = nppesBaseUrl + "?version=2.1&first_name=" + fullName[0] + "&limit=200";
        if (fullName.length == 2)
            url = nppesBaseUrl + "?version=2.1&first_name=" + fullName[0] + "&last_name=" + fullName[1] + "&limit=200";
        if (fullName.length > 2)
            return new ArrayList<>();
        NPPESResponse response = restTemplate.getForObject(url, NPPESResponse.class);
        if (response.result_count == 0)
            return null;
        else
            return response.results.stream().map(res -> nppesMapper.basic_map(res)).collect(Collectors.toList());
    }
}
