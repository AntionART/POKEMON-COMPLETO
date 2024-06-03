package com.pokemonapp;

import com.pokemonapp.entities.Movimiento;
import com.pokemonapp.entities.Tipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDAO {
    private Connection connection;
    
    public TipoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarTipo(Tipo tipo) throws SQLException {
        String sql = "INSERT INTO tipo (nombre) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo.getNombre());
            stmt.executeUpdate();
        }
    }

    public Tipo obtenerTipo(int id) throws SQLException {
        String sql = "SELECT * FROM tipo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Tipo tipo = new Tipo(rs.getInt("id"), rs.getString("nombre"));
                tipo.setMovimientos(obtenerMovimientosParaTipo(id));
                return tipo;
            }
        }
        return null;
    }

    private List<Movimiento> obtenerMovimientosParaTipo(int tipoId) throws SQLException {
        List<Movimiento> movimientos = new ArrayList<>();
        String sql = "SELECT m.id, m.nombre FROM movimiento m " +
                     "JOIN tipo_movimiento tm ON m.id = tm.movimiento_id " +
                     "WHERE tm.tipo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tipoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                movimientos.add(new Movimiento(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return movimientos;
    }
}