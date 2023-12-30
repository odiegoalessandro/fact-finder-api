package com.fact.finder.services;

import com.fact.finder.dto.DataSourceUselessFactsApi;
import com.fact.finder.model.FunFact;
import com.fact.finder.repository.FunFactRespository;
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

    @Autowired
    private FunFactRespository factRespository;

    public UselessApiService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    @Scheduled(fixedRate = 10 * 200)
    @Transactional
    public void fetchData() {
        String url = "https://useless-facts.sameerkumar.website/api";

        ResponseEntity<DataSourceUselessFactsApi> response =
                this.restTemplate.getForEntity(url, DataSourceUselessFactsApi.class);


        String body = Objects.requireNonNull(response.getBody()).body();

        if (factRespository.findByTitle(body) == null) {
            FunFact fact = new FunFact();

            fact.setBody(body);
            fact.setTitle(body);
            fact.setSource(url);

            factRespository.save(fact);
        } else {
            System.out.println("Fato não adicionado ao banco de dados por ja possuir cadastro.");
        }
    }

}
