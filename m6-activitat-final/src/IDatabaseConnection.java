import java.util.Collection;

import org.xmldb.api.base.XMLDBException;

public interface IDatabaseConnection {
    public void connect() throws XMLDBException;
    public org.xmldb.api.base.Collection getCollection(String collectionName) throws XMLDBException;
    public boolean createCollection(String collectionName);
}