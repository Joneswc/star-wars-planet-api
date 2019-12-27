package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.controller;

import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.Planet;
import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.repository.PlanetRepository;
import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.service.SequenceGeneratorService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("starwars/planet")
public class PlanetController {

    private PlanetRepository repository;
    private SequenceGeneratorService sequenceGenerator;

    public PlanetController(PlanetRepository repository, SequenceGeneratorService sequenceGenerator) {
        this.repository = repository;
        this.sequenceGenerator = sequenceGenerator;
    }

    @PostMapping("/addplanet")
    public Planet addNewPlanet (@RequestBody Planet planet) {
        planet.setId(sequenceGenerator.generateSequence(Planet.SEQUENCE_NAME));
        return repository.save(planet);
    }

    @GetMapping("/findname")
    public Planet findByName (String name) {
        return repository.findByNome(name);
    }

    @GetMapping("/findid")
    public Planet findById (String id) {
        Optional<Planet> planet = repository.findById(id);
        return planet.get();
    }

}
