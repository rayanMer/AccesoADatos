package R3_UD2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoMariaDB {
	private final int saldoInicial;
	private final int númeroDeCuentas;
	private boolean abierto;
	private static Connection conexión;

	public BancoMariaDB(int numCuentas, int saldoInicial) {

		this.abierto = true;
		this.saldoInicial = saldoInicial;
		this.númeroDeCuentas = numCuentas;

		try {
			conexión = DriverManager.getConnection("jdbc:mariadb://localhost:3306/adat8", "dam2", "asdf.1234");

			// Inicializa la base de datos de cuentas:
			Statement sql = conexión.createStatement();
			sql.execute("DROP TABLE IF EXISTS cuentas ");
			sql.execute("create table cuentas(id int primary key, saldo float)");
			for (int i = 0; i < numCuentas; i++) {
				sql.execute(String.format("insert into cuentas values(%d,%d)", i, saldoInicial));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			conexión = null;
			this.abierto = false;
		}
	}

	public static void crearProcedimientoAlmacenado(Connection conexion) {
		String procedimientoSQL = "DELIMITER //"
				+ "CREATE PROCEDURE transfiere_fondos(IN origen INT, IN destino INT, IN cantidad FLOAT) //" + "BEGIN //"
				+ "   DECLARE saldoOrigen FLOAT; //"
				+ "   SELECT saldo INTO saldoOrigen FROM cuentas WHERE id = origen; //"
				+ "   IF saldoOrigen >= cantidad THEN //"
				+ "       UPDATE cuentas SET saldo = saldo - cantidad WHERE id = origen; //"
				+ "       UPDATE cuentas SET saldo = saldo + cantidad WHERE id = destino; //" + "   ELSE //"
				+ "       SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Fondos insuficientes'; //" + "   END IF; //"
				+ "END //" + "DELIMITER ;";

		try (Statement stmt = conexion.createStatement()) {
			stmt.execute(procedimientoSQL);
			System.out.println("Procedimiento almacenado creado con éxito.");
		} catch (SQLException e) {
			System.err.println("Error creando el procedimiento almacenado: " + e.getMessage());
		}
	}

	public void transfiere(int origen, int destino, float cantidad, Connection conexion) {
		String consulta = "CALL transfiere_fondos(?, ?, ?)"; 

		try (CallableStatement stmt = conexion.prepareCall(consulta)) {
			stmt.setInt(1, origen);
			stmt.setInt(2, destino);
			stmt.setFloat(3, cantidad);

			stmt.execute();
			System.out.println("Transferencia realizada con éxito.");

		} catch (SQLException e) {
			System.err.println("Error ejecutando la transferencia: " + e.getMessage());
		}
	}

	public void comprueba() throws SQLException {
		int saldoTotal = 0;
		Statement sql = conexión.createStatement();
		ResultSet res = sql.executeQuery("SELECT SUM(saldo) FROM cuentas");
		if (res.next()) {
			saldoTotal = (int) res.getFloat(1);
			if (saldoTotal != (númeroDeCuentas * saldoInicial)) {
				System.err.println("¡¡¡¡¡No cuadran las cuentas!!!! saldo total " + saldoTotal);
			} else {
				System.out.println("Balance correcto");
			}
		}

		res = sql.executeQuery("SELECT id FROM cuentas WHERE saldo<0");
		while (res.next()) {
			System.err.println("DESCUBIERTO en cuenta " + res.getInt(1));
		}

		/*
		 * Detallando por cuenta: ResultSet res =
		 * sql.executeQuery("SELECT id,saldo FROM cuentas"); while (res.next()) { int
		 * saldo = (int) res.getFloat(2); saldoTotal += saldo;
		 * System.out.printf("Cuenta %d , saldo %d, parcial %d\n", res.getInt(1),
		 * saldo,* saldoTotal); }
		 */

	} // comprueba

	public int getNúmeroDeCuentas() {
		return númeroDeCuentas;
	}

	boolean abierto() {
		return abierto;
	}

	void cierraBanco() {
		abierto = false;

	}

	void cierraConexiónBD() {
		try {
			conexión.close();
		} catch (SQLException e) {
			System.err.println("Error cerrando conexión de BBDD " + e.getMessage());
		}
	}
}
