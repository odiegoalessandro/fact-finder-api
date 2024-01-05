package com.fact.finder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
public class RedditApiService implements DataSource {

    private final String baseUrl = "https://www.reddit.com/";

    @Autowired
    private RedditAuthService redditAuthService;

    @Autowired
    private FactService factService;

    private List<String> getSubredditList() {
        return Stream.of("facts", "science", "Awwducational")
                .map(subreddit -> "r/" + subreddit + "/new/.json?limit=100").toList();
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 10 * 200)
    public void fetchData() {
        List<String> endpoints = getSubredditList();

        System.out.println(endpoints);

    }
}
