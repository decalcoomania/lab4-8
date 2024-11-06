package credit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditOfferManagerTest {
    private CreditOfferManager creditOfferManager;
    private PersonalCreditOffer personalOffer;
    private BusinessCreditOffer businessOffer;

    @BeforeEach
    void setUp() {
        creditOfferManager = new CreditOfferManager();
        personalOffer = new PersonalCreditOffer("Test Bank", 5.5, 12, true, false, 10000.0);
        businessOffer = new BusinessCreditOffer("Another Bank", 6.0, 24, false, true, 50000.0, 5000.0);
    }

    @Test
    void testAddCreditOffer() {
        creditOfferManager.addCreditOffer(personalOffer);
        List<CreditOffer> offers = creditOfferManager.getCreditOffers();

        assertEquals(1, offers.size());
        assertEquals(personalOffer, offers.get(0));
    }

    @Test
    void testDeleteCreditOfferByIndex() {
        creditOfferManager.addCreditOffer(personalOffer);
        creditOfferManager.addCreditOffer(businessOffer);

        creditOfferManager.deleteCreditOfferByIndex(0);

        List<CreditOffer> offers = creditOfferManager.getCreditOffers();
        assertEquals(1, offers.size());
        assertEquals(businessOffer, offers.get(0));
    }

    @Test
    void testDeleteCreditOfferByIndexInvalidIndex() {
        creditOfferManager.addCreditOffer(personalOffer);

        creditOfferManager.deleteCreditOfferByIndex(1); // Invalid index

        List<CreditOffer> offers = creditOfferManager.getCreditOffers();
        assertEquals(1, offers.size()); // Should still be 1
    }

    @Test
    void testDeleteCreditOfferByBank() {
        creditOfferManager.addCreditOffer(personalOffer);
        creditOfferManager.addCreditOffer(businessOffer);

        creditOfferManager.deleteCreditOfferByBank("Test Bank");

        List<CreditOffer> offers = creditOfferManager.getCreditOffers();
        assertEquals(1, offers.size());
        assertEquals(businessOffer, offers.get(0)); // Only business offer should remain
    }

    @Test
    void testSearchByRate() {
        creditOfferManager.addCreditOffer(personalOffer);
        creditOfferManager.addCreditOffer(businessOffer);

        List<CreditOffer> results = creditOfferManager.searchByRate(5.5);
        assertEquals(1, results.size());
        assertEquals(personalOffer, results.get(0));
    }

    @Test
    void testSearchByBank() {
        creditOfferManager.addCreditOffer(personalOffer);
        creditOfferManager.addCreditOffer(businessOffer);

        List<CreditOffer> results = creditOfferManager.searchByBank("Another Bank");
        assertEquals(1, results.size());
        assertEquals(businessOffer, results.get(0));
    }

    @Test
    void testSaveToFile() throws IOException {
        creditOfferManager.addCreditOffer(personalOffer);
        String filename = "test_credit_offers.dat";

        creditOfferManager.saveToFile(filename);

        // Verify file is created and contains data
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            List<CreditOffer> loadedOffers = (List<CreditOffer>) in.readObject();
            assertEquals(1, loadedOffers.size());
            assertEquals(personalOffer, loadedOffers.get(0));
        } catch (ClassNotFoundException e) {
            fail("Class not found when loading credit offers.");
        }
    }

    @Test
    void testLoadFromFile() throws IOException, ClassNotFoundException {
        creditOfferManager.addCreditOffer(personalOffer);
        String filename = "test_credit_offers.dat";
        creditOfferManager.saveToFile(filename);

        CreditOfferManager newManager = new CreditOfferManager();
        newManager.loadFromFile(filename);

        List<CreditOffer> loadedOffers = newManager.getCreditOffers();
        assertEquals(1, loadedOffers.size());
        assertEquals(personalOffer, loadedOffers.get(0));
    }

    @Test
    void testBusinessCreditOffer() {
        double expectedMinTurnover = 5000.0;
        BusinessCreditOffer offer = new BusinessCreditOffer("Test Bank", 6.0, 24, false, true, 50000.0, expectedMinTurnover);

        assertEquals(expectedMinTurnover, offer.getMinTurnover()); // Check min turnover
    }
}
