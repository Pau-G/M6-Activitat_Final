import java.io.File;
import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class CreateXmlFile {

    public static void main(String[] args) {

        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("cataleg.xml"));

            // Start document
            writer.writeStartDocument();

            // Start cataleg element
            writer.writeStartElement("cataleg");

            // Start cd element
            writer.writeStartElement("cd");
            writer.writeAttribute("id", "1");

            // titol element
            writer.writeStartElement("titol");
            writer.writeCharacters("Tinta Roja");
            writer.writeEndElement();

            // artista element
            writer.writeStartElement("artista");
            writer.writeCharacters("Andres Calamaro");
            writer.writeEndElement();

            // pais element
            writer.writeStartElement("pais");
            writer.writeCharacters("Argentina");
            writer.writeEndElement();

            // preu element
            writer.writeStartElement("preu");
            writer.writeCharacters("10.90");
            writer.writeEndElement();

            // any element
            writer.writeStartElement("any");
            writer.writeCharacters("2006");
            writer.writeEndElement();

            // End cd element
            writer.writeEndElement();

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
