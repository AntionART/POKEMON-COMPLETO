package com.pokemonapp;

import com.pokemonapp.entities.Movimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovimientoDAO {

    public void agregarMovimiento(Movimiento movimiento) throws SQLException {
        String sql = "INSERT INTO movimientos (nombre) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, movimiento.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar el movimiento: " + e.getMessage());
            throw e;
        }
    }

    public Movimiento obtenerMovimiento(int id) throws SQLException {
        String sql = "SELECT * FROM movimientos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Movimiento(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el movimiento: " + e.getMessage());
            throw e;
        }
        return null;
    }
}
