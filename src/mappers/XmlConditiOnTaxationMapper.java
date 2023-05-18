package mappers;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import models.ConditionTaxation;
import models.Tarif;
import parsers.XmlParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XmlConditiOnTaxationMapper {
	
	final private static String filePath = "./XML_files/conditiontaxation.xml";

	public static List<ConditionTaxation> map(Element rootElement) {
		
		NodeList objectConditionTaxationList = rootElement.getElementsByTagName("ObjectConditionTaxation");

		List<ConditionTaxation> conditionTaxations = new ArrayList<ConditionTaxation>();

		for (int i = 0; i < objectConditionTaxationList.getLength(); i++) {
			Element objectConditionTaxation = (Element) objectConditionTaxationList.item(i);
			
			String idClientStr = objectConditionTaxation.getElementsByTagName("idClient").item(0).getTextContent();
			String taxePortDuStr = objectConditionTaxation.getElementsByTagName("taxePortDu").item(0).getTextContent();
			BigDecimal taxePortDu = new BigDecimal(taxePortDuStr);
			String taxePortPayeStr = objectConditionTaxation.getElementsByTagName("taxePortPaye").item(0).getTextContent();
			BigDecimal taxePortPaye = new BigDecimal(taxePortPayeStr);
			Boolean useTaxePortDuGenerale = Boolean.parseBoolean(objectConditionTaxation.getElementsByTagName("useTaxePortDuGenerale").item(0).getTextContent());
			Boolean useTaxePortPayeGenerale = Boolean.parseBoolean(objectConditionTaxation.getElementsByTagName("useTaxePortPayeGenerale").item(0).getTextContent());

			ConditionTaxation conditionTaxation = new ConditionTaxation(taxePortDu, taxePortPaye, useTaxePortDuGenerale, useTaxePortPayeGenerale);
			if(!idClientStr.equals(null) && !idClientStr.isEmpty()) {
				conditionTaxation.setIdClient(Integer.parseInt(idClientStr));
			}
			conditionTaxations.add(conditionTaxation);
		}

		return conditionTaxations;
	}
	
	public static List<ConditionTaxation>getConditionTaxations() {
		
		Element xmlContent = XmlParser.parse(filePath);
		return map(xmlContent);
		
	}

}