package com.pokemonapp;

import com.pokemonapp.entities.Pokemon;
import com.pokemonapp.entities.Tipo;
import com.pokemonapp.entities.Movimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {

    private Connection connection;

    // Constructor que acepta una conexión
    public PokemonDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarPokemon(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO pokemon (nombre) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pokemon.getNombre());
            stmt.executeUpdate();
        }
    }

    public Pokemon obtenerPokemon(int id) throws SQLException {
        String sql = "SELECT * FROM pokemon WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pokemon pokemon = new Pokemon(rs.getInt("id"), rs.getString("nombre"));
                pokemon.setTipos(obtenerTiposParaPokemon(id));
                pokemon.setMovimientos(obtenerMovimientosParaPokemon(id));
                return pokemon;
            }
        }
        return null;
    }

    private List<Tipo> obtenerTiposParaPokemon(int pokemonId) throws SQLException {
        List<Tipo> tipos = new ArrayList<>();
        String sql = "SELECT t.id, t.nombre FROM tipo t " +
                     "JOIN pokemon_tipo pt ON t.id = pt.tipo_id " +
                     "WHERE pt.pokemon_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tipos.add(new Tipo(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return tipos;
    }

    private List<Movimiento> obtenerMovimientosParaPokemon(int pokemonId) throws SQLException {
        List<Movimiento> movimientos = new ArrayList<>();
        String sql = "SELECT m.id, m.nombre FROM movimiento m " +
                     "JOIN pokemon_movimiento pm ON m.id = pm.movimiento_id " +
                     "WHERE pm.pokemon_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pokemonId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movimientos.add(new Movimiento(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return movimientos;
    }

    void addPokemon(Pokemon pokemon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Pokemon getPokemon(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
