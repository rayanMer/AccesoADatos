package reto2UD2.apartado2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ContadorSqlTransaccionalResUpdateable {

	public static void main(String[] args) throws ClassNotFoundException {
		// Prueba de concepto de transacción con bloqueo de fila para lectura
		// Sería más fácil en el propio sql poner un set cuenta=cuenta+1 pero ilustramos
		// aquí el problema de concurrencia entre varios procesos.
		// con el for update + transacción conseguimos el bloque de fila y atomicidad
		String sqlConsulta = "select nombre,cuenta from contadores where nombre='contador1' for update;";
		
		
		try (Connection connection = DriverManager.getConnection(
				"jdbc:h2:./databses/apartado2.db", "dam2", "asdf.1234"))
		{
			PreparedStatement consulta = connection.prepareStatement(sqlConsulta,ResultSet.TYPE_FORWARD_ONLY,
																			ResultSet.CONCUR_UPDATABLE);
			int cuenta = 0;
			
			for (int i=0; i<1000; i++) {
				connection.setAutoCommit(false);
				ResultSet res = consulta.executeQuery();
				if (res.next()) {
					cuenta = res.getInt(2);
					cuenta++;
					// Exije que nombre sea clave primaria !!!!
					res.updateInt(2, cuenta);
					res.updateRow();
				}
				else break;
				connection.commit();
				connection.setAutoCommit(false);
			} // for
			System.out.println("Valor final: " + cuenta);
		} catch (Exception e) {
			e.printStackTrace();
		} // try
	} // main
} // class ContadorSql
