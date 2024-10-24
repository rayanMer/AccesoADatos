import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebasJDBC {

	public static void main(String[] args)  {
		String url="jdbc:mysql://localhost:3306/adat";
		try (Connection con = DriverManager.getConnection(url, "dam2", "asdf.1234");){
			String consulta="Select * from alumnos";
			
			Statement sentencia = con.createStatement();
			sentencia.execute(consulta);
			ResultSet resultado = sentencia.executeQuery(consulta);
			System.out.print("ID|");
			System.out.print("Nombre|");
			System.out.print("Apellido1|");
			System.out.println("Apellido2|");

			while(resultado.next()) {
				System.out.print(resultado.getString(1)+"|");
				System.out.print(resultado.getString(2)+"|");
				System.out.print(resultado.getString(3)+"|");
				System.out.println(resultado.getString(4) +"|");

			}
			
			Statement insert = con.createStatement();
			String pepe = "hola";
			String consultaInsert="Insert into alumnos values(null,'rayan','merimi',null)";
			insert.executeUpdate(consultaInsert);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
