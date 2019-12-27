package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.controller;

import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.Planet;
import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.repository.PlanetRepository;
import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.service.SequenceGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/findname/{name}")
    public Planet findByName (@PathVariable String name) {
        return repository.findByNome(name);
    }

    @GetMapping("/findid/{id}")
    public Planet findById (@PathVariable String id) {
        Optional<Planet> planet = repository.findById(id);
        return planet.get();
    }

    @GetMapping("deleteid/{id}")
    public ResponseEntity<String> deletePlanetById (@PathVariable String id ) {
        Planet planet = findById(id);
         repository.delete(planet);
         return new ResponseEntity<String>("planet successfully deleted", HttpStatus.OK);
    }

    @GetMapping("deletename/{name}")
    public ResponseEntity<String> deletePlanetByName (@PathVariable String name ) {
        Planet planet = findByName(name);
        repository.delete(planet);
        return new ResponseEntity<String>("planet successfully deleted", HttpStatus.OK);
    }

}
