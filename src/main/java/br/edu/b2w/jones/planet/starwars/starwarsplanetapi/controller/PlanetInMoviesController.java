package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.controller;

import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.ListBody;
import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.service.APIStarWarsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("starwars/movies")
public class PlanetInMoviesController {

    RestTemplate restTemplate;
    APIStarWarsService service;

    public PlanetInMoviesController(RestTemplate restTemplate, APIStarWarsService service) {
        this.restTemplate = restTemplate;
        this.service = service;
    }

    @GetMapping("/appearances/{planet}")
    public ResponseEntity<String> amountTimesInMovies (@PathVariable String planet) throws IOException {
        Integer amount = service.getAmount(planet);
        return new ResponseEntity<String>(amount.toString(), HttpStatus.OK);
    }

}
