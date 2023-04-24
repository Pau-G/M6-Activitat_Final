import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class CreateXmlFile {

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
}
