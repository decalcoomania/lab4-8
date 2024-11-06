package commands;

import credit.CreditOfferManager;
import credit.CreditOffer;

import java.util.List;
import java.util.Scanner;

public class SearchCreditOfferByBank implements Command { // Реалізуйте інтерфейс Command

    private CreditOfferManager manager;
    private Scanner scanner;

    public SearchCreditOfferByBank(CreditOfferManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() { // Додано переоприділення методу execute
        System.out.print("Введіть назву банку: ");
        String bankName = scanner.nextLine();
        List<CreditOffer> offers = manager.searchByBank(bankName);

        if (offers.isEmpty()) {
            System.out.println("Немає кредитних пропозицій із такою назвою банку: " + bankName);
        } else {
            offers.forEach(System.out::println);
        }
    }
}
