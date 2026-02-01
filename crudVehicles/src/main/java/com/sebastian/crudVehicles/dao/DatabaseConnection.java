package com.sebastian.crudVehicles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sebastian.crudVehicles.models.Car;

public class DatabaseConnection {

    public void saveCar(Car car) {


        String sql = "INSERT INTO cars (brand, model, year_car, num_doors, pedals, fuel) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = com.sebastian.crudVehicles.config.DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, car.getBrand());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setInt(4, car.getNumDoors());
            pstmt.setString(5, car.getPedals());
            pstmt.setString(6, car.getFuel());

            pstmt.executeUpdate();
            System.out.println("¡Vehículo guardado en la base de datos con éxito!");

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}