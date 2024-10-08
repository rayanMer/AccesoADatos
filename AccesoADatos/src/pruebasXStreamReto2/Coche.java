package pruebasXStreamReto2;


public class Coche {
	private String nombre;
	private String modelo;
	private int cv;
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void setCv(int cv) {
		this.cv = cv;
	}
	public Coche() {
		
	}
	public String getNombre() {
		return nombre;
	}
	public String getModelo() {
		return modelo;
	}
	public int getCv() {
		return cv;
	}
	@Override
	public String toString() {
		return "Coche [nombre=" + nombre + ", modelo=" + modelo + ", cv=" + cv + "]";
	}

}
