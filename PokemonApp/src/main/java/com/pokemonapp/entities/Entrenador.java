package com.pokemonapp.entities;

import java.util.List;

public class Entrenador {
    private final int id;
    private final String nombre;
    private final List<Pokemon> pokemones;

    public Entrenador(int id, String nombre, List<Pokemon> pokemones) {
        this.id = id;
        this.nombre = nombre;
        this.pokemones = pokemones;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pokemon> getPokemones() {
        return pokemones;
    }
}
