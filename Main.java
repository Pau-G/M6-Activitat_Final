import java.util.Scanner;

import org.xmldb.api.base.XMLDBException;

public class Main {
    public static void main(String[] args) throws XMLDBException {
        
        // Connectar a base de datos
        IDatabaseConnection connection = DatabaseConnection.getInstance();
        connection.connect();

        // Menu amb les diferents opocions possibles
        System.out.println("------MENU------");
        System.out.println("1. Crear Document XML");
        Scanner sc = new Scanner(System.in);

    }
}
