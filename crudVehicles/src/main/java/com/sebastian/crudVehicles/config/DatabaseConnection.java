package com.sebastian.crudVehicles.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream in = DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties props = new Properties();
            if (in != null) {
                props.load(in);
                url = props.getProperty("spring.datasource.url");
                username = props.getProperty("spring.datasource.username");
                password = props.getProperty("spring.datasource.password");
            }

            if (url == null || url.isBlank()) {
                url = "jdbc:postgresql://localhost:5432/js_vehicles";
            }
            if (username == null) {
                username = "postgres";
            }
            if (password == null) {
                password = "";
            }

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                                System.err.println("Warning: PostgreSQL JDBC Driver not found on classpath: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error loading database configuration: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
