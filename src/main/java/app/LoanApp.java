package app;

import commands.*;
import credit.CreditOfferManager;
import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class LoanApp {
    private static CreditOfferManager creditOfferManager;
    private static Scanner scanner;
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    public static void main(String[] args) {
        creditOfferManager = new CreditOfferManager();
        scanner = new Scanner(System.in);
        String filename = "credit_offers.dat";

        // Завантаження кредитних пропозицій
        loadCreditOffers(filename);

        // Головне меню
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            if (choice == -1) {
                continue; // Продовжити цикл у разі некоректного вводу
            }

            Command command = createCommand(choice);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Invalid choice."); // Повідомлення про некоректний вибір
            }
        }
    }

    public static void loadCreditOffers(String filename) {
        try {
            creditOfferManager.loadFromFile(filename);
            System.out.println("Credit offers loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }

    public static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add credit offer");
        System.out.println("2. Search credit offer by rate");
        System.out.println("3. Search credit offer by bank name");
        System.out.println("4. Save credit offers to file");
        System.out.println("5. Delete credit offer by index");
        System.out.println("6. Delete credit offers by bank name");
        System.out.println("7. Show all credit offers");
        System.out.println("8. Exit");
        System.out.print("Select an option: ");
    }

    public static int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера
            return choice;
        } catch (Exception e) {
            errorLogger.error(ERROR_MARKER, "Error parsing appliance data from file."
                    + e.getMessage(), e);
            System.out.println("Invalid input type.");
            scanner.nextLine(); // Очищення буфера при помилці
            return -1; // Повернути -1 для вказання некоректного вибору
        }
    }

    public static Command createCommand(int choice) {
        switch (choice) {
            case 1:
                return new AddCreditOffer(creditOfferManager, scanner);
            case 2:
                return new SearchCreditOfferByRate(creditOfferManager, scanner);
            case 3:
                return new SearchCreditOfferByBank(creditOfferManager, scanner);
            case 4:
                return new SaveCreditOffers(creditOfferManager, "credit_offers.dat");
            case 5:
                return new DeleteCreditOfferByIndex(creditOfferManager, scanner);
            case 6:
                return new DeleteCreditOfferByBank(creditOfferManager, scanner);
            case 7:
                return new DisplayCreditOffers(creditOfferManager);
            case 8:
                System.out.println("Exiting.");
                // Видалити System.exit(0); для тестування
                return null;
            default:
                return null; // Повернути null для некоректного вибору
        }
    }

    public static void setScanner(Scanner scanner) {
        LoanApp.scanner = scanner;
    }

    public static void setManager(CreditOfferManager manager) {
        LoanApp.creditOfferManager = manager;
    }
}
