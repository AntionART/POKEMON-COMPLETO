package com.pokemonapp.entities;

public class Movimiento {
    private final int id;
    private final String nombre;

    public Movimiento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
