package commands;

import credit.CreditOfferManager;
import credit.CreditOffer;

import java.util.List;
import java.util.Scanner;

public class SearchCreditOfferByRate implements Command {
    private final CreditOfferManager manager;
    private final Scanner scanner;

    public SearchCreditOfferByRate(CreditOfferManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        double maxRate = 0.0;
        boolean validInput = false;

        // Цикл для повторення запиту на введення до тих пір, поки введення не буде коректним
        while (!validInput) {
            System.out.print("Введіть максимальну ставку: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Відсутні дані для обробки.");
                return;
            }

            String input = scanner.nextLine();
            try {
                maxRate = Double.parseDouble(input);
                if (maxRate < 0) {
                    System.out.println("Ставка повинна бути невід'ємною. Спробуйте ще раз.");
                    continue;
                }
                validInput = true; // Якщо введення коректне, виходимо з циклу
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть дійсне число.");
            }
        }

        List<CreditOffer> creditOffers = manager.searchByRate(maxRate);
        if (creditOffers.isEmpty()) {
            System.out.println("Немає кредитних пропозицій із такою ставкою.");
        } else {
            System.out.println("Знайдені кредитні пропозиції:");
            for (CreditOffer creditOffer : creditOffers) {
                System.out.println(creditOffer); // Тут ви можете форматувати вивід, якщо потрібно
            }
        }
    }
}
