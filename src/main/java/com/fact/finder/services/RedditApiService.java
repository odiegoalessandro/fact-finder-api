package com.fact.finder.services;

import com.fact.finder.dto.DataSourceRedditResponse;
import com.fact.finder.dto.FactDto;
import com.sun.jdi.InternalException;
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

    private final String baseUrl;
    private final RestTemplate restTemplate;

    @Autowired
    private FactService factService;

    public RedditApiService() {
        this.baseUrl = "https://www.reddit.com/";
        this.restTemplate = new RestTemplate();
    }

    private List<String> getSubredditList() {
        return Stream.of("facts", "science", "Awwducational")
                .map(subreddit -> "r/" + subreddit + "/hot/.json?limit=100").toList();
    }

    public void processEndpoint(String endpoint) {
        String url = baseUrl + endpoint;
        ResponseEntity<DataSourceRedditResponse> redditResponse = restTemplate
                .getForEntity(url, DataSourceRedditResponse.class);

        if (redditResponse.getStatusCode().is2xxSuccessful()) {
            processSucessResponse(redditResponse);
        }

        throw new InternalException("Erro ao buscar dados");
    }

    private void processSucessResponse(ResponseEntity<DataSourceRedditResponse> redditResponse) {
        var children = redditResponse.getBody().data().children().stream().map(DataSourceRedditResponse.Child::data);
        List<FactDto> factDtos = children.map(childData ->
                new FactDto(childData.title(),
                        childData.title(),
                        baseUrl + "r/" + childData.subreddit())).toList();

        factService.saveAll(factDtos);
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 200 * 60)
    public void fetchData() {

        try{
            List<String> endpoints = getSubredditList();
            endpoints.parallelStream().forEach(this::processEndpoint);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
