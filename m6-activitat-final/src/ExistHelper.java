import java.io.File;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

public class ExistHelper {

    DatabaseConnection databaseConnection;
    int autoincrement = 0;

    public ExistHelper() throws XMLDBException {
        databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.connect();
    }

    private void queryToCollectionFile(org.xmldb.api.base.Collection col, String fileName, String query)
            throws XMLDBException {
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        ResourceSet result = servicio.query(query);

        ResourceIterator i;
        i = result.getIterator();
        if (!i.hasMoreResources())
            System.out.println(" LA CONSULTA NO RETORNA RES.");

        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String) r.getContent());
        }
    }

    public void createCollection(String collectionname) throws XMLDBException {
        boolean result = databaseConnection.createCollection(collectionname);
        if (result) {
            System.out.println("Col·leció creada");
        } else {
            System.out.println("Error al crear la col·lecció");
        }
    }

    public void createFile(String collectionName, String fileName, String node) {

        try {

            Collection col = databaseConnection.getCollection(collectionName);

            if (col == null) {
                System.out.println("La col·lecció no existeix");
                return;
            }

            // Crea un nuevo documento XML
            String xml = "<" + node + "></" + node + ">";
            XMLResource resource = (XMLResource) col.createResource(fileName + ".xml", XMLResource.RESOURCE_TYPE);
            resource.setContent(xml);

            col.storeResource(resource);

            col.close();

            System.out.println("XML file created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadXML(String collectionName, String fileName) throws XMLDBException {
        org.xmldb.api.base.Collection col = databaseConnection.getCollection(collectionName);

        File arxiu = new File(fileName);
        if (!arxiu.canRead()) {
            System.out.println("ERROR AL LLEIGIR ARXIU");
        } else {
            org.xmldb.api.base.Resource nouRecurs = col.createResource(arxiu.getName(), "XMLResource");
            nouRecurs.setContent(arxiu); // Comprova que es un arxiu
            col.storeResource(nouRecurs);
        }
    }

    public void insertElement(String collectionName, String fileName, String titol, String artistName,
            String countryName, float price, int year) throws XMLDBException {
        // Desem la coleció desitjada
        org.xmldb.api.base.Collection col = databaseConnection.getCollection(collectionName);

        String query = "update insert " +
                "<cd id='" + autoincrement + "'>" +
                "<titol>" + titol + "</titol>" +
                "<artista>" + artistName + "</artista>" +
                "<pais>" + countryName + "</pais>" +
                "<preu>" + price + "</preu>" +
                "<any>" + year + "</any>" +
                "</cd>" +
                " into doc('" + fileName + ".xml')//cataleg";

        queryToCollectionFile(col, fileName, query);
    }

    public void changeCDPrice(String collectionName, String fileName, String cdTitle, float price)
            throws XMLDBException {
        // Desem la coleció desitjada
        org.xmldb.api.base.Collection col = databaseConnection.getCollection(collectionName);

        String query = "update value //cd[titol = '" + cdTitle + "']/preu with '" + price + "'";

        queryToCollectionFile(col, fileName, query);
    }

    public void countCDs(String collectionName, String fileName) throws XMLDBException {
        org.xmldb.api.base.Collection col = databaseConnection.getCollection(collectionName);

        String query = "<NumTotalCDs>{count(/cataleg/cd)}</NumTotalCDs>";

        queryToCollectionFile(col, fileName, query);
    }

    public void deleteCD(String collectionName, String fileName, String cdTitle) throws XMLDBException {
        org.xmldb.api.base.Collection col = databaseConnection.getCollection(collectionName);

        String query = "for $cd in /cataleg/cd" +
                " where $cd/titol ='" +  cdTitle + "'" +
                " return (update delete $cd)";

        queryToCollectionFile(col, fileName, query);
    }

    public void getPreviousCDs(String collectionName, String fileName, int year) throws XMLDBException {
        org.xmldb.api.base.Collection col = databaseConnection.getCollection(collectionName);

        String query = "for $cd in /cataleg/cd" +
                " where $cd/any < " + year +
                " return concat('Títol: ', $cd/titol, ', Artista: ', $cd/artista)";

        queryToCollectionFile(col, fileName, query);
    }
}
