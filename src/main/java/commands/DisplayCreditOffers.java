package commands;

import credit.CreditOfferManager;
import credit.CreditOffer;

import java.util.List;

public class DisplayCreditOffers implements Command {
    private final CreditOfferManager manager;

    public DisplayCreditOffers(CreditOfferManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        List<CreditOffer> creditOffers = manager.getCreditOffers();
        if (creditOffers.isEmpty()) {
            System.out.println("Немає доступних кредитних пропозицій.");
        } else {
            for (int i = 0; i < creditOffers.size(); i++) {
                System.out.println(i + ". " + creditOffers.get(i));
            }
        }
    }
}
