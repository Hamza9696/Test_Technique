package mappers;
import org.w3c.dom.*;

import models.Tarif;
import parsers.XmlParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XmlTarifMapper {


	final private static String filePath = "./XML_files/tarif.xml";

	public static List<Tarif> map(Element rootElement) {
		
		NodeList objectTarifList = rootElement.getElementsByTagName("ObjectTarif");

		List<Tarif> tarifs = new ArrayList<Tarif>();

		for (int i = 0; i < objectTarifList.getLength(); i++) {
			Element objectTarif = (Element) objectTarifList.item(i);

			String codeDepartement = objectTarif.getElementsByTagName("codeDepartement").item(0).getTextContent();
			Integer idClient = Integer.parseInt(objectTarif.getElementsByTagName("idClient").item(0).getTextContent());
			String idClientHeritageStr = objectTarif.getElementsByTagName("idClientHeritage").item(0).getTextContent();
			String montantStr = objectTarif.getElementsByTagName("montant").item(0).getTextContent();
			BigDecimal montant = new BigDecimal(montantStr);
			Integer zone = Integer.parseInt(objectTarif.getElementsByTagName("zone").item(0).getTextContent());

			Tarif tarif = new Tarif(codeDepartement, idClient, montant, zone);
			if(!idClientHeritageStr.equals(null) && !idClientHeritageStr.isEmpty()) {
				tarif.setIdClientHeritage(Integer.parseInt(idClientHeritageStr));
			}
			tarifs.add(tarif);
			
		}

		return tarifs;
	}
	
	public static List<Tarif>getTarifs() {
		
		Element xmlContent = XmlParser.parse(filePath);
		return map(xmlContent);
		
	}

}
