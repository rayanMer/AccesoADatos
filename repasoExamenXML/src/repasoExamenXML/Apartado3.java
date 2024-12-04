package repasoExamenXML;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Apartado3 {

	public static void main(String[] args) {
		Libro libro1 = new Libro("Don Quijote", "654654654", 5558);
		Libro libro2 = new Libro("Libro 2", "a888545", 99);
		persistenciaToXml(libro1, libro2);
		ArrayList<Libro> listaRecuperadArrayList = persistenciaFromXML();
		for (Libro libro : listaRecuperadArrayList) {
			System.out.println(libro.toString());
		}
	}

	public static ArrayList<Libro> persistenciaFromXML() {
		XStream xStream = new XStream(new DomDriver());
		xStream.addPermission(AnyTypePermission.ANY);
		xStream.alias("Libro", Libro.class); 

		String ruta = "/home/alumno/eclipse-workspace/repasoExamenXML";
		PersistenceStrategy strategy = new FilePersistenceStrategy(new File(ruta));
		ArrayList<Libro> listaLibros = new ArrayList();
		XmlArrayList listaXMLArrayList = new XmlArrayList(strategy);
		for (Iterator it = listaXMLArrayList.iterator(); it.hasNext();) {
			Libro libro = (Libro) it.next();
			listaLibros.add(libro);
		}
		return listaLibros;
	}

	public static void persistenciaToXml(Libro libro1, Libro libro2) {
		XStream xStream = new XStream(new DomDriver());
		xStream.addPermission(AnyTypePermission.ANY);
		String ruta = "/home/alumno/eclipse-workspace/repasoExamenXML";
		File archivo = new File(ruta);
		XmlArrayList lista = new XmlArrayList(new FilePersistenceStrategy(archivo, xStream));
		xStream.alias("Libro", Libro.class);
		lista.add(libro1);
		lista.add(libro2);
	}

}
