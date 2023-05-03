import java.util.Scanner;

import org.xmldb.api.base.XMLDBException;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ExistHelper existHelper = new ExistHelper();

    private static String askName(String x) {
        String name;
        boolean isCorrect = false;

        do {
            if (!isCorrect) {
                isCorrect = true;
            } else {
                System.out.println("Format incorrecte");
            }

            System.out.print("Indicar " + x);
            name = sc.nextLine();
        } while (name.isEmpty() || name.matches(".*\\s.*") || !name.matches("[a-zA-Z0-9]+"));

        return name;
    }

    private static Float askFloat(String x ) {
        float number;
        boolean isCorrect = false;

        do {
            if (!isCorrect) {
                isCorrect = true;
            } else {
                System.out.println("Format incorrecte");
            }

            System.out.print("Indicar " + x);
            number = sc.nextFloat();
        } while (number > 0);

        return number;
    }

    private static int askInt(String x ) {
        int number;
        boolean isCorrect = false;

        do {
            if (!isCorrect) {
                isCorrect = true;
            } else {
                System.out.println("Format incorrecte");
            }

            System.out.print("Indicar " + x);
            number = sc.nextInt();
        } while (number > 0);

        return number;
    }

    public static void crearColeccio() throws XMLDBException {
        String collectionName = askName("nom");

        existHelper.createCollection(collectionName);
    }

    public static void crearXML() {
        String fileName = askName("nom");
        String mainNode = askName("node");

        existHelper.createFile(fileName, mainNode);
    }

    public static void pujarXML() {
        System.out.println("Indica el nom de la col·lecció a la que vols pujar el fitxer:");
        String collectionName;
        while ((collectionName = sc.nextLine()).isBlank() || !collectionName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el nom del fitxer que vols pujar:");
        String fileName;
        while ((fileName = sc.nextLine()).isBlank() || !fileName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        existHelper.uploadXML(collectionName, fileName);
    }

    public static void inserirElement() {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName;
        while ((collectionName = sc.nextLine()).isBlank() || !collectionName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el nom del fitxer on vols inserir l'element:");
        String fileName;
        while ((fileName = sc.nextLine()).isBlank() || !fileName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el títol del cd:");
        String cdTitle;
        while ((cdTitle = sc.nextLine()).isBlank() || !cdTitle.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el nom de l'artista:");
        String artistName;
        while ((artistName = sc.nextLine()).isBlank() || !artistName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el nom del pais d'origen:");
        String countryName;
        while ((countryName = sc.nextLine()).isBlank() || !countryName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el preu del cd:");
        float price;
        while ((price = sc.nextFloat()) > 0) {
            System.out.println("Format del preu incorrecte");
        }

        System.out.println("Indica l'any del CD:");
        int year;
        while ((year = sc.nextInt()) > 0) {
            System.out.println("Format del preu incorrecte");
        }

        existHelper.insertElement(collectionName, fileName, cdTitle, artistName, countryName, price, year);
    }

    public static void changeCDPrice() {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName;
        while ((collectionName = sc.nextLine()).isBlank() || !collectionName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el nom del fitxer que conté el CD:");
        String fileName;
        while ((fileName = sc.nextLine()).isBlank() || !fileName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el títol del cd:");
        String cdTitle;
        while ((cdTitle = sc.nextLine()).isBlank() || !cdTitle.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el nou preu del CD:");
        float price;
        while ((price = sc.nextFloat()) > 0) {
            System.out.println("Format del preu incorrecte");
        }

        existHelper.changeCDPrice(collectionName, fileName, cdTitle, price);
    }

    public static void countCDsInFile() {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName;
        while ((collectionName = sc.nextLine()).isBlank() || !collectionName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el nom del fitxer:");
        String fileName;
        while ((fileName = sc.nextLine()).isBlank() || !fileName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        existHelper.countCDs(collectionName, fileName);
    }

    public static void deleteCD() {
        System.out.println("Indica el nom de la col·lecció");
        String collectionName;
        while ((collectionName = sc.nextLine()).isBlank() || !collectionName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el nom del fitxer que conté el CD:");
        String fileName;
        while ((fileName = sc.nextLine()).isBlank() || !fileName.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        System.out.println("Indica el títol del cd:");
        String cdTitle;
        while ((cdTitle = sc.nextLine()).isBlank() || !cdTitle.matches("^[a-zA-Z0-9]")) {
            System.out.println("Format del nom incorrecte");
        }

        existHelper.deleteCD(collectionName, fileName, cdTitle);
    }

    public static void getPreviousCDs() {
        System.out.println("Indica l'any límit (veuras els CDs anteriors a aquest any):");
        int year;
        while ((year = sc.nextInt()) > 0) {
            System.out.println("Format del preu incorrecte");
        }

        existHelper.getPreviousCDs(year);
    }

    public static void main(String[] args) throws XMLDBException {
        
        // Instancia de la classe que es connecta a la base de dades
        IDatabaseConnection connection = DatabaseConnection.getInstance();
        // Iniciar la connexió a la base de dades
        connection.connect();

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



        switch(sc.nextInt()) {
            case 1:
                crearColeccio();
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
            default:
                System.out.println("Default");
            break;
        }

    }
}
