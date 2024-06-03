package com.pokemonapp;

import com.pokemonapp.entities.Move;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveDAO {

    private Connection connection;

    public MoveDAO(Connection connection) {
        this.connection = connection;
    }

    public void addMove(Move move) throws SQLException {
        String sql = "INSERT INTO move (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, move.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar el movimiento: " + e.getMessage());
            throw e;
        }
    }

    public Move getMove(int id) throws SQLException {
        String sql = "SELECT * FROM move WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Move(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el movimiento: " + e.getMessage());
            throw e;
        }
        return null;
    }
}