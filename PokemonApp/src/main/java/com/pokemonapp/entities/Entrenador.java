package com.pokemonapp.entities;

import java.util.List;

public class Entrenador {
    private final int id;
    private final String nombre;
    private final List<Pokemon> pokemones;

    public Entrenador(int i, String nombre, java.util.List<com.pokemonapp.entities.Pokemon> pokemones) {
        this.id = i;
        this.nombre = nombre;
        this.pokemones = pokemones;
    }

    public Entrenador(int i, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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