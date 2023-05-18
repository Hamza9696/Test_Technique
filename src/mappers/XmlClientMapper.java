package mappers;
import org.w3c.dom.*;

import models.Client;
import parsers.XmlParser;

import java.util.List;
import java.util.ArrayList;

public class XmlClientMapper {
	
	final private static String filePath = "./XML_files/client.xml";

	private static List<Client> map(Element rootElement) {
		
		NodeList objectClientList = rootElement.getElementsByTagName("ObjectClient");

		List<Client> clients = new ArrayList<Client>();

		for (int i = 0; i < objectClientList.getLength(); i++) {
			Element objectClient = (Element) objectClientList.item(i);
			String codePostal = objectClient.getElementsByTagName("codePostal").item(0).getTextContent();
			Integer idClient = Integer.parseInt(objectClient.getElementsByTagName("idClient").item(0).getTextContent());
			String raisonSociale = objectClient.getElementsByTagName("raisonSociale").item(0).getTextContent();
			String ville = objectClient.getElementsByTagName("ville").item(0).getTextContent();

			Client client = new Client(codePostal, idClient, raisonSociale, ville);
			clients.add(client);
		}

		return clients;
	}
	
	public static List<Client>getClients() {
		
		Element xmlContent = XmlParser.parse(filePath);
		return map(xmlContent);
		
	}

}
