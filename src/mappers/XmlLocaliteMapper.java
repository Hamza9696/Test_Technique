package mappers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import models.Localite;
import parsers.XmlParser;

public class XmlLocaliteMapper {
	final private static String filePath = "./XML_files/localite.xml";

	public static List<Localite> map(Element rootElement) {
		
		NodeList objectLocaliteList = rootElement.getElementsByTagName("ObjectLocalite");

		List<Localite> localites = new ArrayList<Localite>();

		for (int i = 0; i < objectLocaliteList.getLength(); i++) {
			Element objectLocalite = (Element) objectLocaliteList.item(i);
			
			String codePostal = objectLocalite.getElementsByTagName("codePostal").item(0).getTextContent();
			String ville = objectLocalite.getElementsByTagName("ville").item(0).getTextContent();
			Integer zone = Integer.parseInt(objectLocalite.getElementsByTagName("zone").item(0).getTextContent());


			Localite localite = new Localite(codePostal, ville, zone);
			localites.add(localite);
		}

		return localites;
	}
	
	public static List<Localite>getLocalites() {
		
		Element xmlContent = XmlParser.parse(filePath);
		return map(xmlContent);
		
	}

}
