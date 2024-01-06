package com.fact.finder.services;

import com.fact.finder.dto.DataSourceRedditResponse;
import com.fact.finder.dto.FactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class RedditApiService implements DataSource {

    private final String baseUrl = "https://www.reddit.com/";

    @Autowired
    private FactService factService;

    private List<String> getSubredditList() {
        return Stream.of("facts", "science", "Awwducational")
                .map(subreddit -> "r/" + subreddit + "/hot/.json?limit=100").toList();
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 1000 * 60)
    public void fetchData() {
        try {
            List<String> endpoints = getSubredditList();
            RestTemplate restTemplate = new RestTemplate();

            for (String endpoint : endpoints) {
                ResponseEntity<DataSourceRedditResponse> redditResponse = restTemplate.
                        getForEntity(baseUrl + endpoint, DataSourceRedditResponse.class);

                if (redditResponse.getStatusCode().is2xxSuccessful()) {
                    var data = Objects.requireNonNull(redditResponse.getBody()).data();
                    var childs = data.children().stream().map(DataSourceRedditResponse.Child::data);
                    List<FactDto> factsDto =
                            childs.map(child ->
                                    new FactDto(child.title(),
                                            child.title(),
                                            baseUrl + "r/" + child.subreddit())).toList();

                    factService.saveAll(factsDto);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
