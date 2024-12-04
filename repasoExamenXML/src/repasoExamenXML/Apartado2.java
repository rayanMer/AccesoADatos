package repasoExamenXML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Apartado2 {

	public static void main(String[] args) {
		Libro libro1 = new Libro("Don Quijote", "654654654", 5558);
		Libro libro2 = new Libro("Libro 2", "a888545", 99);
		//System.out.println(convertirAXml(libro1));
		//String xmlString = convertirAXml(libro1);
		//System.out.println(recuperarLibro(xmlString).toString());
		guardarFichero(libro2);
		System.out.println(recuperarLibroFichero());
	}
	
	public static String convertirAXml(Libro libro) {
		XStream xStream=new XStream(new DomDriver());
		xStream.addPermission(AnyTypePermission.ANY);
		//Esto es para modifica el alias de libro pasamos de repasoExamen.libro a Libro
		xStream.alias("Libro", Libro.class);
		//Esto es para poder modificar un campo pasamos de nobre a nombreModificado
		xStream.aliasField("nombreModificado", Libro.class, "nombre");
		//Esto es para poner atributos 
		//xStream.aliasAttribute(Libro.class,"isbn", "isbn");
		String xmlString =xStream.toXML(libro);
		return xmlString;
	}
	public static Libro recuperarLibro(String xml) {
		XStream xStream = new XStream(new DomDriver());
		xStream.addPermission(AnyTypePermission.ANY);
		xStream.alias("Libro", Libro.class);
		xStream.aliasField("nombreModificado", Libro.class, "nombre");
		Libro libroRecuperado=(Libro)xStream.fromXML(xml);
		return libroRecuperado;
		
	}
	public static void guardarFichero(Libro libro) {
		try {
			PrintWriter out=new PrintWriter("libro.xml");
			out.print(convertirAXml(libro));
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String recuperarLibroFichero() {
		String linea;
		String libroCadena="";
		try {
			BufferedReader in = new BufferedReader(new FileReader("libro.xml"));
			while((linea=in.readLine())!=null) {
				libroCadena=libroCadena+linea;
			}
			XStream xStream= new XStream(new DomDriver());
			xStream.addPermission(AnyTypePermission.ANY);
			xStream.alias("Libro", Libro.class);
			xStream.aliasField("nombreModificado", Libro.class, "nombre");
			Libro libroRecuperadoFicheroXml=(Libro)xStream.fromXML(libroCadena);
			System.out.println(libroRecuperadoFicheroXml.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return libroCadena;
		
	}

}
