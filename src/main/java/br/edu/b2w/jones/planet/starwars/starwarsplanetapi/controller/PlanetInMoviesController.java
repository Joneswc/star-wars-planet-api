package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.controller;

import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.ListBody;
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

    public PlanetInMoviesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/appearances/{planet}")
    public ResponseEntity<String> amountTimesInMovies (@PathVariable String planet) throws IOException {
        AtomicReference<Integer> qtdAppear = new AtomicReference<>(0);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:55.0) Gecko/20100101 Firefox/55.0");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String firstUri = "https://swapi.co/api/planets/";
        boolean next = true;
        while ( next ) {
            ResponseEntity<String> response = restTemplate.exchange( firstUri , HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            ListBody results = objectMapper.readValue(response.getBody(), new TypeReference<ListBody>(){});
            results.getResults().forEach( result -> {
                if ( result.getName().equals(planet) ) {
                    qtdAppear.set(result.getFilms().length);
                }
            });
            if ( qtdAppear.get().equals(0) ) {
                firstUri = results.getNext();
            } else {
                next = false;
            }
        }
        return new ResponseEntity<String>(qtdAppear.get().toString(), HttpStatus.OK);
    }

}
