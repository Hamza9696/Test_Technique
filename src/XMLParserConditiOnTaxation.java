import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParserConditiOnTaxation {
    public static void main(String[] args) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse("./XML_files/conditiontaxation.xml");

            // Get the root element
            Element rootElement = document.getDocumentElement();

            // Get all "ObjectClient" elements
            NodeList objectConditionTaxationList = rootElement.getElementsByTagName("ObjectConditionTaxation");

            // Iterate through the "ObjectClient" elements
            for (int i = 0; i < objectConditionTaxationList.getLength(); i++) {
                Element objectConditionTaxation = (Element) objectConditionTaxationList.item(i);

                // Get the attributes of the "ObjectClient" element
                String hashcode = objectConditionTaxation.getAttribute("hashcode");
                String type = objectConditionTaxation.getAttribute("type");

                // Get child elements of "ObjectClient"
                String idClient = objectConditionTaxation.getElementsByTagName("idClient").item(0).getTextContent();
                String taxePortDu = objectConditionTaxation.getElementsByTagName("taxePortDu").item(0).getTextContent();
                String taxePortPaye = objectConditionTaxation.getElementsByTagName("taxePortPaye").item(0).getTextContent();
                String useTaxePortDuGenerale = objectConditionTaxation.getElementsByTagName("useTaxePortDuGenerale").item(0).getTextContent();
                String useTaxePortPayeGenerale = objectConditionTaxation.getElementsByTagName("useTaxePortPayeGenerale").item(0).getTextContent();

                // Print the extracted data
                System.out.println("ObjectClient " + (i + 1));
                System.out.println("hashcode: " + hashcode);
                System.out.println("idClient: " + idClient);
                System.out.println("taxePortDu: " + taxePortDu);
                System.out.println("taxePortPaye: " + taxePortPaye);
                System.out.println("useTaxePortDuGenerale: " + useTaxePortDuGenerale);
                System.out.println("useTaxePortPayeGenerale: " + useTaxePortPayeGenerale);
                System.out.println();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
