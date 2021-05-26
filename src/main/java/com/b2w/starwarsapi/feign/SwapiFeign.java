package com.b2w.starwarsapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "Swapi", url = "https://swapi.dev/api/")
public interface SwapiFeign {

    @GetMapping(value = "/planets/", produces = "application/json")
    Object getPlanets();
}
