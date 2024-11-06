package commands;

import credit.CreditOfferManager;
import java.util.Scanner;

public class DeleteCreditOfferByIndex implements Command {
    private final CreditOfferManager manager;
    private final Scanner scanner;

    public DeleteCreditOfferByIndex(CreditOfferManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введіть індекс кредитної пропозиції для видалення: ");
        int index = scanner.nextInt();
        manager.deleteCreditOfferByIndex(index);
        System.out.println("Кредитну пропозицію видалено.");
    }
}
