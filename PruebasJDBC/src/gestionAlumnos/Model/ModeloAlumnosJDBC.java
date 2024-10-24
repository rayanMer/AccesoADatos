package gestionAlumnos.Model;

import java.sql.Connection;

import gestionAlumnos.UI.VentanaAlumnos;
import gestionAlumnos.UI.VentanaAlumnos.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloAlumnosJDBC implements IModeloAlumnos {

	private static String cadenaConexion = "jdbc:mysql://localhost:3306/adat1";
	private static String user = "dam2";
	private static String pass = "asdf.1234";

	public ModeloAlumnosJDBC() {

	}

	@Override
	public List<String> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno getAlumnoPorDNI(String DNI) {

			
		return null;
	}

	@Override
	public boolean modificarAlumno(Alumno alumno) {
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {

			String sql = "update alumnos set nombre='alumno.getNombre()', apellido='alumno.getApellido()',cp'cp.getCP()' where dni='dni.getDNI()'";
			PreparedStatement update = con.prepareStatement(sql);
			
			update.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean eliminarAlumno(String DNI) {
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {

			String sql = "DELETE FROM alumnos WHERE dni ='"+DNI+"'";
			PreparedStatement delete = con.prepareStatement(sql);
			delete.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean crear(Alumno alumno) {
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {

			String sql = "Insert into alumnos(dni,nombre,apellido,cp) values(?,?,?,?)";
			PreparedStatement insert = con.prepareStatement(sql);
			
			insert.setString(1,alumno.getDNI());
			insert.setString(2, alumno.getNombre());
			insert.setString(3,alumno.getApellidos());
			insert.setString(4, alumno.getCP());
			
			insert.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
