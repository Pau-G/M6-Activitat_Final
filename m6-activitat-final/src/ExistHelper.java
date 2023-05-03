import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

public class ExistHelper {

    public void createCollection(String collectionname) throws XMLDBException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        CollectionManagementService managementService = (CollectionManagementService)databaseConnection.col.getService("CollectionManagementService","1.0");
        managementService.createCollection(collectionname);
    }

    public void createFile(String fileName, String node) {

        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(fileName + ".xml"));

            // Start document
            writer.writeStartDocument();

            // Start cataleg element
            writer.writeStartElement(node);

            // End cataleg element
            writer.writeEndElement();

            // End document
            writer.writeEndDocument();

            // Flush and close writer
            writer.flush();
            writer.close();

            System.out.println("XML file created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadXML(String collectionName, String fileName) {

    }

    public void insertElement(String collectionName, String fileName, String elementName, String artistName, String countryName, float price, int year) {

    }

    public void changeCDPrice(String collectionName, String fileName, String cdTitle, float price) {

    }

    public void countCDs(String collectionName, String fileName) {

    }

    public void deleteCD(String collectionName, String fileName, String cdTitle) {

    }

    public void getPreviousCDs(int year) {
        
    }
}
