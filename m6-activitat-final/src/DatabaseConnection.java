import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;

public class DatabaseConnection implements IDatabaseConnection {

    public static DatabaseConnection instance;
    
    String driver = "org.exist.xmldb.DatabaseImpl"; // Driver para eXist
    String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/"; // URI colecci�n
    String usu = "admin"; // Usuario
    String usuPwd = "2003"; // Clave

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() throws XMLDBException {

        try {
            Class cl = Class.forName(driver); // Cargar del driver
            Database database = (Database) cl.getDeclaredConstructor().newInstance(); // Instancia de la BD
            DatabaseManager.registerDatabase(database); // Registro del driver

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
    }

    public boolean createCollection(String collectionName) {
        try {
            Collection collection = DatabaseManager.getCollection(URI, usu, usuPwd);
            CollectionManagementService service = (CollectionManagementService) collection.getService("CollectionManagementService", "1.0");
            service.createCollection(collectionName);

            return true;
        } catch (XMLDBException e) {
            return false;
        }
    }

    public org.xmldb.api.base.Collection getCollection(String collectionName) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(URI + collectionName, usu, usuPwd); // Connexió amb la nostra col·lecció

        if (col == null)
            System.out.println("La col·lecció no existeix");

        return col;
    }
}
