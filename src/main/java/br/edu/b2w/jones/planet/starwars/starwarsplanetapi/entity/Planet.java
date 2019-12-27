package br.edu.b2w.jones.planet.starwars.starwarsplanetapi.entity;

import org.springframework.data.annotation.Id;

public class Planet {

    @Id
    private Long id;
    private String nome;
    private String clima;
    private String terreno;

    public Planet() {}

    public Planet(String nome, String clima, String terreno) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

}
