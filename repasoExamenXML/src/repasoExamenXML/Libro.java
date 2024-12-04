package repasoExamenXML;

public class Libro {
	private String nombre;
	private String isbn;
	private int numPagina;
	public Libro(String nombre, String isbn, int numPagina) {
		this.nombre = nombre;
		this.isbn = isbn;
		this.numPagina = numPagina;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getNumPagina() {
		return numPagina;
	}
	public void setNumPagina(int numPagina) {
		this.numPagina = numPagina;
	}
	@Override
	public String toString() {
		return "Libro [nombre=" + nombre + ", isbn=" + isbn + ", numPagina=" + numPagina + "]";
	}
	

}
