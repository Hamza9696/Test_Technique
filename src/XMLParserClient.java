import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParserClient {
    public static void main(String[] args) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse("./XML_files/client.xml");

            // Get the root element
            Element rootElement = document.getDocumentElement();

            // Get all "ObjectClient" elements
            NodeList objectClientList = rootElement.getElementsByTagName("ObjectClient");

            // Iterate through the "ObjectClient" elements
            for (int i = 0; i < objectClientList.getLength(); i++) {
                Element objectClient = (Element) objectClientList.item(i);

                // Get the attributes of the "ObjectClient" element
                String hashcode = objectClient.getAttribute("hashcode");
                String type = objectClient.getAttribute("type");

                // Get child elements of "ObjectClient"
                String codePostal = objectClient.getElementsByTagName("codePostal").item(0).getTextContent();
                String idClient = objectClient.getElementsByTagName("idClient").item(0).getTextContent();
                String raisonSociale = objectClient.getElementsByTagName("raisonSociale").item(0).getTextContent();
                String ville = objectClient.getElementsByTagName("ville").item(0).getTextContent();

                // Print the extracted data
                System.out.println("ObjectClient " + (i + 1));
                System.out.println("hashcode: " + hashcode);
                System.out.println("type: " + type);
                System.out.println("codePostal: " + codePostal);
                System.out.println("idClient: " + idClient);
                System.out.println("raisonSociale: " + raisonSociale);
                System.out.println("ville: " + ville);
                System.out.println();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
