import org.xmldb.api.base.XMLDBException;

public interface IDatabaseConnection {
    public void connect() throws XMLDBException;
}