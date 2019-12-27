package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.controller;

import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.Planet;
import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.repository.PlanetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("starwars/planet")
public class PlanetController {

    private PlanetRepository repository;

    public PlanetController(PlanetRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/addplanet")
    public Planet addNewPlanet (@RequestBody Planet planet) {
        return repository.save(planet);
    }

    @GetMapping("/findname")
    public Planet findByName (String name) {
        return repository.findByNome(name);
    }

    @GetMapping("/findid")
    public Planet findById (Long id) {
        Optional<Planet> planet = repository.findById(id);
        return planet.get();
    }

}
