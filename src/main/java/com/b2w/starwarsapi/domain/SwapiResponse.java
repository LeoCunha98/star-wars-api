package com.b2w.starwarsapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SwapiResponse {

    @JsonProperty("results")
    private List<PlanetasFeign> results;

    public SwapiResponse(List<PlanetasFeign> results) {
        this.results = results;
    }

    public SwapiResponse(){}

    public List<PlanetasFeign> getResults() {
        return results;
    }

    public void setResults(List<PlanetasFeign> results) {
        this.results = results;
    }
}
