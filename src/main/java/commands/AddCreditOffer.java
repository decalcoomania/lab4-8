package commands;

import credit.CreditOfferManager;
import credit.PersonalCreditOffer;
import credit.BusinessCreditOffer;
import java.util.Scanner;

public class AddCreditOffer implements Command { // Додано реалізацію інтерфейсу Command
    private final CreditOfferManager manager;
    private final Scanner scanner;

    public AddCreditOffer(CreditOfferManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    @Override
    public void execute() { // Додано переоприділення методу execute
        System.out.println("Виберіть тип кредитної пропозиції: 1 - особистий, 2 - бізнес");
        int choice = scanner.nextInt();

        System.out.println("Введіть строк кредиту (в місяцях):");
        int term = scanner.nextInt();

        System.out.println("Чи можливе дострокове погашення? (1 - так, 0 - ні):");
        boolean earlyRepayment = scanner.nextInt() == 1;

        if (choice == 2) {
            System.out.println("Введіть мінімальний обіг:");
            double minTurnover = scanner.nextDouble();

            System.out.println("Введіть максимальну суму кредиту:");
            double maxBusinessLoanAmount = scanner.nextDouble();

            System.out.println("Введіть ставку:");
            double rate = scanner.nextDouble();

            System.out.println("Введіть назву банку:");
            scanner.nextLine(); // очищення буферу
            String bankName = scanner.nextLine();

            BusinessCreditOffer offer = new BusinessCreditOffer(bankName, rate, term, earlyRepayment, true, maxBusinessLoanAmount, minTurnover);
            manager.addCreditOffer(offer);
        } else {
            System.out.println("Введіть максимальну суму кредиту:");
            double maxLoanAmount = scanner.nextDouble();

            System.out.println("Введіть ставку:");
            double rate = scanner.nextDouble();

            System.out.println("Введіть назву банку:");
            scanner.nextLine(); // очищення буферу
            String bankName = scanner.nextLine();

            PersonalCreditOffer offer = new PersonalCreditOffer(bankName, rate, term, earlyRepayment, false, maxLoanAmount);
            manager.addCreditOffer(offer);
        }
    }
}
