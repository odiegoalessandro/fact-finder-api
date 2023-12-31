package com.fact.finder.dto;

public class FactDto {
    private final String body;
    private final String title;
    private final String source;

    public FactDto(String body, String title, String source) {
        this.body = body;
        this.title = title;
        this.source = source;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }
}
