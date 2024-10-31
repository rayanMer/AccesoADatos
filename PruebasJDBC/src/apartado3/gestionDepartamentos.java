package apartado3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class gestionDepartamentos {
	private static String cadenaConexion = "jdbc:mysql://localhost:3306/adat9";
	private static String user = "dam2";
	private static String pass = "asdf.1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Seleccione una opción:");
        System.out.println("1. Mostrar todos los países");
        System.out.println("2. Crear un país");
        System.out.println("3. Modificar un país");
        System.out.println("4. Eliminar un país");
        int opt = scanner.nextInt();

        switch (opt) {
            case 1:
                mostrarTodos();
                break;
            case 2:
                System.out.println("Introduce ID del país:");
                String id = scanner.next();
                System.out.println("Introduce el nombre del país:");
                String nombre = scanner.next();
                System.out.println("Introduce el ID de la región:");
                String regionId = scanner.next();

                Countries country = new Countries(id, nombre, regionId);
                crearCountry(country);
                break;
            case 3:
                System.out.println("Introduce el ID del país a modificar:");
                String modId = scanner.next();
                System.out.println("Introduce el nuevo nombre del país:");
                String nuevoNombre = scanner.next();
                System.out.println("Introduce el nuevo ID de la región:");
                String nuevaRegionId = scanner.next();

                Countries countryMod = new Countries(modId, nuevoNombre, nuevaRegionId);
                modificarPais(countryMod);
                break;
            case 4:
                System.out.println("Introduce el ID del país a eliminar:");
                String elimId = scanner.nextLine();
                eliminarPais(elimId);
                break;
            default:
                System.err.println("Opción incorrecta");
        }

        scanner.close();
    }

    public static void mostrarTodos() {
        System.out.println("Mostrando todos los países...");
    }

    public static boolean crearCountry(Countries country) {
        try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass)) {

            String sql = "INSERT INTO countries (country_id, country_name, region_id) VALUES (?, ?, ?)";
            PreparedStatement insert = con.prepareStatement(sql);
            insert.setString(1, country.getCountry_id());
            insert.setString(2, country.getCountry_name());
            insert.setString(3, country.getRegion_id());
            insert.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error al crear country: " + e.getMessage());
        }
        return false;
    }


    public static void modificarPais(Countries country) {
        System.out.println("Modificando país: " + country);
    }

    public static void eliminarPais(String countryId) {
        System.out.println("Eliminando país con ID: " + countryId);
    }
}
