import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParserTarif {
    public static void main(String[] args) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse("./XML_files/tarif.xml");

            // Get the root element
            Element rootElement = document.getDocumentElement();

            // Get all "ObjectClient" elements
            NodeList objectTarifList = rootElement.getElementsByTagName("ObjectTarif");

            // Iterate through the "ObjectClient" elements
            for (int i = 0; i < objectTarifList.getLength(); i++) {
                Element objectTarif = (Element) objectTarifList.item(i);

                // Get the attributes of the "ObjectClient" element
                String hashcode = objectTarif.getAttribute("hashcode");
                String type = objectTarif.getAttribute("type");

                // Get child elements of "ObjectClient"
                String codeDepartement = objectTarif.getElementsByTagName("codeDepartement").item(0).getTextContent();
                String idClient = objectTarif.getElementsByTagName("idClient").item(0).getTextContent();
                String montant = objectTarif.getElementsByTagName("montant").item(0).getTextContent();
                String zone = objectTarif.getElementsByTagName("zone").item(0).getTextContent();

                // Print the extracted data
                System.out.println("ObjectClient " + (i + 1));
                System.out.println("hashcode: " + hashcode);
                System.out.println("type: " + type);
                System.out.println("codeDepartement: " + codeDepartement);
                System.out.println("idClient: " + idClient);
                System.out.println("montant: " + montant);
                System.out.println("zone: " + zone);
                System.out.println();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
