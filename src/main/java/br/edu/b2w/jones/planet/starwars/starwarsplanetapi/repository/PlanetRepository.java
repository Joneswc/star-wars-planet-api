package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.repository;

import br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    public Planet findByNome(String nome);
    public Optional<Planet> findById(String id);

}
