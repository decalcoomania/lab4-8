package credit;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LoanApp {
    public static void main(String[] args) {
        CreditOfferManager creditOfferManager = new CreditOfferManager();
        Scanner scanner = new Scanner(System.in);
        String filename = "credit_offers.dat";

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати кредитну пропозицію");
            System.out.println("2. Пошук кредитної пропозиції за ставкою");
            System.out.println("3. Зберегти кредитні пропозиції у файл");
            System.out.println("4. Завантажити кредитні пропозиції з файлу");
            System.out.println("5. Показати всі кредитні пропозиції");
            System.out.println("6. Вийти");
            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCreditOffer(scanner, creditOfferManager);
                    break;
                case 2:
                    searchCreditOfferByRate(scanner, creditOfferManager);
                    break;
                case 3:
                    try {
                        creditOfferManager.saveToFile(filename);
                        System.out.println("Кредитні пропозиції збережено у файл.");
                    } catch (IOException e) {
                        System.out.println("Помилка при збереженні: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        creditOfferManager.loadFromFile(filename);
                        System.out.println("Кредитні пропозиції завантажено з файлу.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Помилка при завантаженні: " + e.getMessage());
                    }
                    break;
                case 5:
                    displayCreditOffers(creditOfferManager);
                    break;
                case 6:
                    System.out.println("Вихід.");
                    return;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }

    private static void addCreditOffer(Scanner scanner, CreditOfferManager creditOfferManager) {
        scanner.nextLine();  // Чистимо сканер
        System.out.print("Введіть назву банку: ");
        String bankName = scanner.nextLine();
        System.out.print("Введіть ставку (%): ");
        double rate = scanner.nextDouble();
        System.out.print("Введіть термін (міс.): ");
        int term = scanner.nextInt();
        System.out.print("Дострокове погашення (1 - так, 0 - ні): ");
        boolean earlyRepayment = scanner.nextInt() == 1;
        System.out.print("Збільшення кредитної лінії (1 - так, 0 - ні): ");
        boolean creditLineIncrease = scanner.nextInt() == 1;

        CreditOffer creditOffer = new PersonalCreditOffer(bankName, rate, term, earlyRepayment, creditLineIncrease);
        creditOfferManager.addCreditOffer(creditOffer);
        System.out.println("Кредитну пропозицію додано.");
    }

    private static void searchCreditOfferByRate(Scanner scanner, CreditOfferManager creditOfferManager) {
        System.out.print("Введіть максимальну ставку: ");
        double maxRate = scanner.nextDouble();
        List<CreditOffer> creditOffers = creditOfferManager.searchByRate(maxRate);
        if (creditOffers.isEmpty()) {
            System.out.println("Немає кредитних пропозицій із такою ставкою.");
        } else {
            for (CreditOffer creditOffer : creditOffers) {
                System.out.println(creditOffer);
            }
        }
    }

    private static void displayCreditOffers(CreditOfferManager creditOfferManager) {
        List<CreditOffer> creditOffers = creditOfferManager.getCreditOffers();
        if (creditOffers.isEmpty()) {
            System.out.println("Немає кредитних пропозицій для відображення.");
        } else {
            for (CreditOffer creditOffer : creditOffers) {
                System.out.println(creditOffer);
            }
        }
    }
}
