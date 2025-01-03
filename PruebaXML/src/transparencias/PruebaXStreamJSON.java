package transparencias;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;


/**
 *  @descrition
 *	@author Laura
 *  @date 6/5/2015
 *  @version 1.0
 *  @license GPLv3
 */

public class PruebaXStreamJSON {

	public static void main(String[] args) {
		XStream xstream = new XStream(new JettisonMappedXmlDriver()); 
		xstream.addPermission(AnyTypePermission.ANY);
        xstream.alias("product", Persona.class);
        
        Telefono tel=new Telefono();
		tel.setCodigo(34);
		tel.setNumero(666666666);
		Persona persona=new Persona();
		persona.setNombre("Pepe");
		persona.setApellido("Garcia");
		persona.setTelefono(tel);
		
		String jsonPersona = xstream.toXML(persona);
        System.out.println(xstream.toXML(persona));	
        
        Persona persona2 = (Persona) xstream.fromXML(jsonPersona);
        System.out.println(persona2);
	}

}


