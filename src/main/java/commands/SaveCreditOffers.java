package commands;

import credit.CreditOfferManager;
import java.io.IOException;

public class SaveCreditOffers implements Command {
    private final CreditOfferManager manager;
    private final String filename;

    public SaveCreditOffers(CreditOfferManager manager, String filename) {
        this.manager = manager;
        this.filename = filename;
    }

    @Override
    public void execute() {
        try {
            manager.saveToFile(filename);
            System.out.println("Кредитні пропозиції збережено у файл.");
        } catch (IOException e) {
            System.out.println("Помилка при збереженні: " + e.getMessage());
        }
    }
}
