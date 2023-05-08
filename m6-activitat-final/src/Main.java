import java.util.Scanner;

import org.xmldb.api.base.XMLDBException;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static ExistHelper existHelper;

    private static String askName(String x) {
        String name;
        boolean isCorrect = false;

        do {
            if (!isCorrect) {
                isCorrect = true;
            } else {
                System.out.println("Format incorrecte");
            }

            System.out.print(x + ": ");
            name = sc.nextLine();
        } while (name.isEmpty() || name.matches(".*\\s.*") || !name.matches("[a-zA-Z0-9]+"));

        return name;
    }

    private static Float askFloat(String x) {
        float number;
        boolean isCorrect = false;

        do {
            if (!isCorrect) {
                isCorrect = true;
            } else {
                System.out.println("Format incorrecte");
            }

            System.out.print(x + ": ");
            number = Float.parseFloat(sc.nextLine());

        } while (number <= 0f);

        return number;
    }

    private static int askInt(String x) {
        int number;
        boolean isCorrect = false;

        do {
            if (!isCorrect) {
                isCorrect = true;
            } else {
                System.out.println("Format incorrecte");
            }

            System.out.print(x + ": ");
            number = Integer.parseInt(sc.nextLine());
        } while (number <= 0);
        return number;
    }

    public static void crearColeccio() throws XMLDBException {
        String collectionName = askName("nom");

        existHelper.createCollection(collectionName);
    }

    public static void crearXML() {
        System.out.println("Indica la col·leció on vols crear el fitxer");
        String collectionName = askName("Coleccio");

        System.out.println("Indica el nom de l'arxiu");
        String fileName = askName("Nom");

        System.out.println("Indica el nom del node inicial");
        String mainNode = askName("Node");

        existHelper.createFile(collectionName, fileName, mainNode);
    }

    public static void pujarXML() throws XMLDBException {
        System.out.println("Indica el nom de la col·lecció a la que vols pujar el fitxer");
        String collectionName = askName("Nom");

        System.out.println("Indica el nom del fitxer que vols pujar");
        String fileName = askName("Nom");

        existHelper.uploadXML(collectionName, fileName);
    }

    public static void inserirElement() throws XMLDBException {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName = askName("Nom");

        System.out.println("Indica el nom del fitxer on vols inserir l'element");
        String fileName = askName("Nom");

        System.out.println("Indica el títol del cd");
        String cdTitle = askName("Títol");

        System.out.println("Indica el nom de l'artista");
        String artistName = askName("Nom");

        System.out.println("Indica el nom del pais d'origen");
        String countryName = askName("Nom");

        System.out.println("Indica el preu del cd");
        float price = askFloat("Preu");

        System.out.println("Indica l'any del CD");
        int year = askInt("Any");

        existHelper.insertElement(collectionName, fileName, cdTitle, artistName, countryName, price, year);
    }

    public static void changeCDPrice() throws XMLDBException {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName = askName("Nom");

        System.out.println("Indica el nom del fitxer que conté el CD");
        String fileName = askName("Nom");

        System.out.println("Indica el títol del cd");
        String cdTitle = askName("Títol");

        System.out.println("Indica el nou preu del CD");
        float price = askFloat("Preu");

        existHelper.changeCDPrice(collectionName, fileName, cdTitle, price);
    }

    public static void countCDsInFile() throws XMLDBException {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName = askName("Nom");

        System.out.println("Indica el nom del fitxer:");
        String fileName = askName("Nom");

        existHelper.countCDs(collectionName, fileName);
    }

    public static void deleteCD() throws XMLDBException {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName = askName("Nom");

        System.out.println("Indica el nom del fitxer que conté el CD");
        String fileName = askName("Nom");

        System.out.println("Indica el títol del cd");
        String cdTitle = askName("Títol");

        existHelper.deleteCD(collectionName, fileName, cdTitle);
    }

    public static void getPreviousCDs() throws XMLDBException {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName = askName("Nom");

        System.out.println("Indica el nom del fitxer que conté el CD");
        String fileName = askName("Nom");

        System.out.println("Indica l'any límit (veuras els CDs anteriors a aquest any)");
        int year = askInt("Any");

        existHelper.getPreviousCDs(collectionName, fileName, year);
    }

    public static void main(String[] args) throws XMLDBException {
        existHelper = new ExistHelper();
        
        // Instancia de la classe que es connecta a la base de dades
        IDatabaseConnection connection = DatabaseConnection.getInstance();
        // Iniciar la connexió a la base de dades
        connection.connect();

        while(true) {
            // Menu amb les diferents opocions possibles
            System.out.println("------MENU------");
            System.out.println("1. Crear Col·lecció");
            System.out.println("2. Crear Document XML");
            System.out.println("3. Pujar Document a una col·lecció");
            System.out.println("4. Inserir element en un fitxer");
            System.out.println("5. Canviar el preu d'un CD");
            System.out.println("6. Calcular número de CDs en un fitxer");
            System.out.println("7. Delete CD");
            System.out.println("8. Veure discs anteriors a un cert any");
            System.out.println("9. Sortir");


            int option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 1:
                    crearColeccio();
                    break;
                case 2:
                    crearXML();
                break;
                case 3:
                    pujarXML();
                break;
                case 4:
                    inserirElement();
                break;
                case 5:
                    changeCDPrice();
                break;
                case 6:
                    countCDsInFile();
                break;
                case 7:
                    deleteCD();
                break;
                case 8:
                    getPreviousCDs();
                break;
                case 9:
                    System.exit(0);
                break;
                default:
                    System.out.println("Default");
                break;
            }
        }

    }
}
