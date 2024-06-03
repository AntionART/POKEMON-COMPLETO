package com.pokemonapp.entities;

import java.util.List;

public class Trainer {
    private final int id;
    private final String name;
    private List<Pokemon> pokemons;

    public Trainer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Trainer(int id, String nombre, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
