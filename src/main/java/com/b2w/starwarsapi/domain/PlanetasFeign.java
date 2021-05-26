package com.b2w.starwarsapi.domain;

import java.util.List;

public class PlanetasFeign {
    private String name;
    private List<String> films;

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
