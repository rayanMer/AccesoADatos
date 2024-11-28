import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pokemon {
	@Id
	String nombre;
	String tipo;
	public Pokemon(String nombre, String tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
}
