package com.sebastian.crudVehicles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sebastian.crudVehicles.dao.CarDAO;
import com.sebastian.crudVehicles.models.Car;

@SpringBootApplication
public class CrudVehiclesApplication implements CommandLineRunner {

	private final CarDAO carDAO;

	public CrudVehiclesApplication(CarDAO carDAO) {
		this.carDAO = carDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudVehiclesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Asegurar tabla `cars` antes de insertar (evita error "relation \"cars\" does not exist")
		try (java.sql.Connection conn = com.sebastian.crudVehicles.config.DatabaseConnection.getConnection();
			 java.sql.Statement stmt = conn.createStatement()) {
			String ddl = "CREATE TABLE IF NOT EXISTS cars (" +
				"id SERIAL PRIMARY KEY," +
				"brand VARCHAR(100) NOT NULL," +
				"model VARCHAR(100) NOT NULL," +
				"year_car INTEGER NOT NULL," +
				"num_doors INTEGER NOT NULL," +
				"pedals VARCHAR(50)," +
				"fuel VARCHAR(50)" +
				")";
			stmt.execute(ddl);
			System.out.println("Tabla 'cars' asegurada (existente o creada) desde CrudVehiclesApplication.");
		} catch (Exception e) {
			System.err.println("No se pudo crear/verificar la tabla 'cars' en el arranque: " + e.getMessage());
		}

		Car car = new Car("Toyota", "Corolla", 2020, 4, "manual", "gasoline");
		long id = carDAO.saveCar(car);
		if (id > 0) {
			System.out.println("Vehículo insertado con id = " + id);
		} else if (id == 0) {
			System.out.println("Inserción realizada pero no se devolvió id generado.");
		} else {
			System.err.println("Error al insertar vehículo, revisar logs.");
		}
	}
}
