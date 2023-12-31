package com.fact.finder.services;

import com.fact.finder.dto.DataSourceUselessFactsApi;
import com.fact.finder.dto.FactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class UselessApiService implements DataSource {
    private final RestTemplate restTemplate;
    private final String url;

    @Autowired
    private FactService factService;

    public UselessApiService() {
        this.url = "https://useless-facts.sameerkumar.website/api";
        this.restTemplate = new RestTemplate();
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 10 * 200)
    public void fetchData() {
        try {
            ResponseEntity<DataSourceUselessFactsApi> response =
                    this.restTemplate.getForEntity(url, DataSourceUselessFactsApi.class);

            String body = Objects.requireNonNull(response.getBody()).body();
            FactDto fact = new FactDto(body, body, url);

            factService.save(fact);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
