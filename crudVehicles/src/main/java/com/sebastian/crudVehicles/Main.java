import java.sql.Connection;
import java.sql.Statement;

void main() {
    // Asegurarse de que la tabla `cars` exista antes de insertar
    try (Connection conn = com.sebastian.crudVehicles.config.DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement()) {

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
        IO.println("Tabla 'cars' asegurada (existente o creada).");
    } catch (Exception e) {
        System.err.println("No se pudo crear/verificar la tabla 'cars': " + e.getMessage());
        // continuar (el DAO también fallará si la tabla no existe)
    }

    com.sebastian.crudVehicles.dao.CarDAO dao = new com.sebastian.crudVehicles.dao.CarDAO();
    com.sebastian.crudVehicles.models.Car car = new com.sebastian.crudVehicles.models.Car("Toyota", "Corolla", 2020, 4, "manual", "gasoline");
    long id = dao.saveCar(car);
    if (id > 0) {
        IO.println("Vehículo insertado con id = " + id);
    } else if (id == 0) {
        IO.println("Inserción realizada pero no se devolvió id generado.");
    } else {
        System.err.println("Error al insertar vehículo, revisar logs.");
    }
}