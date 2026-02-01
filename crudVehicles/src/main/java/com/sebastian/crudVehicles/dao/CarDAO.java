package com.sebastian.crudVehicles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.sebastian.crudVehicles.models.Car;

@Repository
public class CarDAO {

    /**
     * Guarda un Car en la tabla `cars` y devuelve el id generado.
     * Retorna -1 si ocurre un error, 0 si la inserción no devolvió clave.
     */
    public long saveCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("car no puede ser null");
        }

        String sql = "INSERT INTO cars (brand, model, year_car, num_doors, pedals, fuel) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = com.sebastian.crudVehicles.config.DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, car.getBrand());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setInt(4, car.getNumDoors());
            pstmt.setString(5, car.getPedals());
            pstmt.setString(6, car.getFuel());

            int affected = pstmt.executeUpdate();
            if (affected == 0) {
                System.err.println("No se insertó ninguna fila.");
                return -1;
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }

            return 0;
        } catch (SQLException e) {
            System.err.println("Error al guardar: " + e.getMessage());
            // Evitar printStackTrace() para cumplir con buenas prácticas; mostrar causa si existe
            if (e.getCause() != null) {
                System.err.println("Causa: " + e.getCause());
            }
            return -1;
        }
    }
}
