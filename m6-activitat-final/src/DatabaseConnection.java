<<<<<<< HEAD
import java.io.File;
import java.io.PrintStream;
import java.util.Collection;

import javax.xml.crypto.Data;

=======
>>>>>>> 2d96b8c284e8f3e9025acf61393482634156f84f
import org.xmldb.api.*;
import org.xmldb.api.base.*;

public class DatabaseConnection implements IDatabaseConnection {

	public static DatabaseConnection instance;

	private DatabaseConnection() {
	}

	public static DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

	public Database database;

	public void connect() throws XMLDBException {

		String driver = "org.exist.xmldb.DatabaseImpl"; // Driver para eXist
		org.xmldb.api.base.Collection col = null; // Colecci�n
		String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/"; // URI colecci�n
		String usu = "admin"; // Usuario
		String usuPwd = "2003"; // Clave

		try {
			Class cl = Class.forName(driver); // Cargar del driver
			Database database = (Database) cl.newInstance(); // Instancia de la BD
			DatabaseManager.registerDatabase(database); // Registro del driver

		} catch (Exception e) {
			System.out.println("Error al inicializar la BD eXist");
			e.printStackTrace();
		}

		col = DatabaseManager.getCollection(URI, usu, usuPwd); // Connexi� amb la nostra col�lecci�

		if (col == null)
			System.out.println(" *** LA COLECCION NO EXISTE. ***");

		// Exemple de consulta XQuery

		XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");

		// Ara farem i executarem la consulta xquery sobre el nostre fitxer
		ResourceSet result = servicio.query("for $a in //title/text() return $a");

		// Exemple de com mostrar els resultats de la nostra consulta.
		ResourceIterator i;
		i = result.getIterator();
		if (!i.hasMoreResources())
			System.out.println(" LA CONSULTA NO DEVUELVE NADA.");

		col.close(); // Cerramos coleccion
	}
}
