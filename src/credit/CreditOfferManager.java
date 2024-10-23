package credit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreditOfferManager {
    private List<CreditOffer> creditOffers;

    public CreditOfferManager() {
        creditOffers = new ArrayList<>();
    }

    public void addCreditOffer(CreditOffer creditOffer) {
        creditOffers.add(creditOffer);
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(creditOffers);
        }
    }

    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            creditOffers = (List<CreditOffer>) ois.readObject();
        }
    }

    public List<CreditOffer> searchByRate(double maxRate) {
        List<CreditOffer> result = new ArrayList<>();
        for (CreditOffer creditOffer : creditOffers) {
            if (creditOffer.getInterestRate() <= maxRate) {
                result.add(creditOffer);
            }
        }
        return result;
    }

    public List<CreditOffer> getCreditOffers() {
        return creditOffers;
    }
}
