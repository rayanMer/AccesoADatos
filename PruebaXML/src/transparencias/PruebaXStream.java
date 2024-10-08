package transparencias;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

/**
 *  @descrition Primera clase de prueba de XStream;
 *	@author Laura
 *  @date 29/4/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class PruebaXStream {

	public static void main(String[] args) {
		
		//Instanciamos un objeto XStream: requiere un parser XML para funcionar: 3 posibles formas de instanciarlo
		
		//Forma 1:
		//Para usar el constructor por defecto necesitamos en el classpath XPP3, un parser XML externo muy potente.
		//XStream xstream = new XStream();
		
		//Forma 2:
		//Si no queremos incluir la dependencia con la libreria XPP3 podemos utilizar un parser JAXP DOM
		//para ello importamos el paquete com.thoughtworks.xstream.io.xml.DomDriver
		XStream xstream1 = new XStream(new DomDriver());
		xstream1.addPermission(AnyTypePermission.ANY);
		//Forma 3:
		//Si no queremos incluir la dependencia con la libreria XPP3 a partir de Java 6 tambi�n podemos usar un parser StAX
		//para ello importamos el paquete com.thoughtworks.xstream.io.xml.StaxDriver
		XStream xstream2 = new XStream(new StaxDriver());
		xstream2.addPermission(AnyTypePermission.ANY);

		//Construimos el objeto  a convertir despu�s en XML
		Telefono tel=new Telefono();
		tel.setCodigo(34);
		tel.setNumero(666666666);
		Persona persona=new Persona();
		persona.setNombre("Pepe");
		persona.setApellido("Garcia");
		persona.setTelefono(tel);
		
		//Convertimos el objeto persona a xml invocando el m�todo toXML
		String xml1=xstream1.toXML(persona);
		String xml2=xstream2.toXML(persona);

		System.out.println(xml1);		
		
		//Reconstruimos un objeto persona a partir del XML generado invocando el m�todo fromXML
		Persona persona2=(Persona)xstream1.fromXML(xml2);
		Persona persona3=(Persona)xstream1.fromXML(xml1);

		
		System.out.println(persona2.toString());
		System.out.println(persona3.toString());

		

	}

}


