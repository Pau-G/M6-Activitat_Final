import org.exist.client.ResourceDescriptor.Collection;

public class DatabaseConnection {
    public void connect(){
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
		Collection col = null; // Colecci�n
		String URI="xmldb:exist://localhost:8080/exist/xmlrpc/db/"; //URI colecci�n
		String usu="XXXXX"; //Usuario
		String usuPwd="XXXXXXX"; //Clave
    }
}
