import org.exist.client.ResourceDescriptor.Collection;
import org.exist.management.impl.Database;
import org.exist.xmldb.DatabaseImpl;

public class CreateCollections {

    public void CreateCollection(String collectionName){

        // Create a new collection named "mycollection"
        Collection newCollection = root.createCollection(collectionName);
        
        // Print success message
        System.out.println("Collection " + collectionName + " created successfully.");
        
        // Release resources
        newCollection.close();
        root.close();
    }
}