package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.controller;

import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.Planet;
import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.PlanetsInAPI;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("starwars/movies")
public class PlanetInMoviesController {

    RestTemplate restTemplate;

    public PlanetInMoviesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(name = "/appearances")
    public HttpEntity<PlanetsInAPI> planetsInMovies () {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<PlanetsInAPI> response = restTemplate.exchange("https://swapi.co/api/films/", HttpMethod.GET, entity, PlanetsInAPI.class);
        return response;
    }

}
