import java.io.FilenameFilter;
import java.util.Scanner;

import org.xmldb.api.base.XMLDBException;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void crearXML() {

        System.out.println("Indica el nom del fitxer:");
        String fileName;
        while ((fileName = sc.nextLine()).isBlank() || !fileName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Inidica el nom del node arrel:");
        String mainNode;
        while ((mainNode = sc.nextLine()).isBlank() || !mainNode.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        CreateXmlFile createXmlFile = new CreateXmlFile();
        createXmlFile.createFile(fileName, mainNode);
    }

    public static void main(String[] args) throws XMLDBException {
        
        // Instancia de la classe que es connecta a la base de dades
        IDatabaseConnection connection = DatabaseConnection.getInstance();
        // Iniciar la connexió a la base de dades
        connection.connect();

        // Menu amb les diferents opocions possibles
        System.out.println("------MENU------");
        System.out.println("1. Crear Document XML");

        switch(sc.nextInt()) {
            case 1:
                crearXML();
            break;
            default:
                System.out.println("Default");
            break;
        }

    }
}
