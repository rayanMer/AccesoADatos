package repasoExamenXML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;

public class Apartado1 {

	public static void main(String[] args) {
		Libro libro1 = new Libro("Don quijote", "654654654", 5558);
		Libro libro2 = new Libro("Libro 2", "a888545", 99);
		//System.out.println(convertirAXml(libro1));
	//	Libro libroRecuperado=recuperarObjetoXml(convertirAXml(libro2));
		//System.out.println(libroRecuperado.toString());
		guardaLibro(libro2);
		String xmlString=recuperarLibro();
		System.out.println(xmlString);
	}
	
	public static String convertirAXml(Libro libro) {
		XStream xStream = new XStream(new DomDriver());
		xStream.addPermission(AnyTypePermission.ANY);
		return xStream.toXML(libro);
	}
	public static Libro recuperarObjetoXml(String xml) {
		XStream xStream= new XStream(new DomDriver());
		xStream.addPermission(AnyTypePermission.ANY);
		Libro libroRecuperadoLibro=(Libro) xStream.fromXML(xml);
		libroRecuperadoLibro.toString();
		return libroRecuperadoLibro;
	}
	public static void guardaLibro(Libro libro) {
		try {
			PrintWriter out=new PrintWriter("libro.xml");
			out.print(convertirAXml(libro));
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public static String recuperarLibro() {
		String linea;
		String libroXMLString="";
		try {
			BufferedReader in = new BufferedReader(new FileReader("libro.xml"));
			while((linea=in.readLine()) !=null) {
				libroXMLString=libroXMLString+linea;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return libroXMLString;
		
	}

}
