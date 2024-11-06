package commands;

import credit.CreditOfferManager;

import java.util.Scanner;

public class DeleteCreditOfferByBank implements Command {
    private final CreditOfferManager manager;
    private final Scanner scanner;

    public DeleteCreditOfferByBank(CreditOfferManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введіть назву банку для видалення кредитних пропозицій: ");
        String bankName = scanner.nextLine();
        manager.deleteCreditOfferByBank(bankName);
        System.out.println("Кредитні пропозиції видалено.");
    }
}
