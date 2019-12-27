package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.controller;

import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.ListBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("starwars/movies")
public class PlanetInMoviesController {

    RestTemplate restTemplate;

    public PlanetInMoviesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/appearances")
    public ListBody planetsInMovies () throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:55.0) Gecko/20100101 Firefox/55.0");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("https://swapi.co/api/films/", HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ListBody results = objectMapper.readValue(response.getBody(), new TypeReference<ListBody>(){});
        return results;
    }

}
