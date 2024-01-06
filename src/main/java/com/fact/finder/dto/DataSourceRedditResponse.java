package com.fact.finder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSourceRedditResponse(@JsonProperty("data") Data data) {

    public record Data(@JsonProperty("after") String after,
                       @JsonProperty("dist") int dist,
                       @JsonProperty("modhash") String modhash,
                       @JsonProperty("geo_filter") String geo_filter,
                       @JsonProperty("children") List<Child> children,
                       @JsonProperty("before") Object before) {
    }

    public record Child(@JsonProperty("kind") String kind,
                        @JsonProperty("data") ChildData data) {
    }

    public record ChildData(@JsonProperty("subreddit") String subreddit,
                            @JsonProperty("selftext") String selftext,
                            @JsonProperty("title") String title,
                            @JsonProperty("author_fullname") String author_fullname) {
    }
}
