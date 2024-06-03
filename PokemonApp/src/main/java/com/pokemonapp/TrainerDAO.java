package com.pokemonapp;

import com.pokemonapp.entities.Trainer;
import com.pokemonapp.entities.Pokemon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {
    private Connection connection;

    public TrainerDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTrainer(Trainer trainer) throws SQLException {
        String sql = "INSERT INTO trainer (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, trainer.getName());
            stmt.executeUpdate();
        }
    }

    public Trainer getTrainer(int id) throws SQLException {
        String sql = "SELECT * FROM trainer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Trainer trainer = new Trainer(rs.getInt("id"), rs.getString("name"));
                trainer.setPokemons(getPokemonsForTrainer(id));
                return trainer;
            }
        }
        return null;
    }

    private List<Pokemon> getPokemonsForTrainer(int trainerId) throws SQLException {
        List<Pokemon> pokemons = new ArrayList<>();
        String sql = "SELECT p.id, p.name FROM pokemon p " +
                     "JOIN trainer_pokemon tp ON p.id = tp.pokemon_id " +
                     "WHERE tp.trainer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, trainerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pokemon pokemon = new Pokemon(rs.getInt("id"), rs.getString("name"));
                pokemons.add(pokemon);
            }
        }
        return pokemons;
    }

    public void agregarEntrenador(Trainer entrenador) throws SQLException {
        String query = "INSERT INTO trainer (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entrenador.getName());
            statement.executeUpdate();
        }
    }

    public Trainer obtenerEntrenador(int id) throws SQLException {
        String query = "SELECT * FROM trainer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("name");
                    return new Trainer(id, nombre, null);
                }
            }
        }
        return null;
    }
}