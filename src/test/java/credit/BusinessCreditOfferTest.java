package credit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BusinessCreditOfferTest {

    @Test
    void testBusinessCreditOfferConstructor() {
        String bankName = "Test Bank";
        double rate = 5.5;
        int term = 24;
        boolean earlyRepayment = true;
        boolean creditLineIncrease = false;
        double maxBusinessLoanAmount = 200000.0;
        double minTurnover = 100000.0;

        BusinessCreditOffer offer = new BusinessCreditOffer(bankName, rate, term, earlyRepayment, creditLineIncrease, maxBusinessLoanAmount, minTurnover);

        assertEquals(bankName, offer.getBankName());
        assertEquals(rate, offer.getRate(), 0.001);
        assertEquals(term, offer.getTerm());
        assertTrue(offer.isEarlyRepayment());
        assertFalse(offer.isCreditLineIncrease());
        assertEquals(maxBusinessLoanAmount, offer.getMaxBusinessLoanAmount());
        assertEquals(minTurnover, offer.getMinTurnover());
    }

    @Test
    void testGetMaxBusinessLoanAmount() {
        double maxBusinessLoanAmount = 250000.0;
        BusinessCreditOffer offer = new BusinessCreditOffer("Test Bank", 5.5, 24, true, false, maxBusinessLoanAmount, 100000.0);

        assertEquals(maxBusinessLoanAmount, offer.getMaxBusinessLoanAmount());
    }

    @Test
    void testGetMinTurnover() {
        double minTurnover = 120000.0;
        BusinessCreditOffer offer = new BusinessCreditOffer("Test Bank", 5.5, 24, true, false, 200000.0, minTurnover);

        assertEquals(minTurnover, offer.getMinTurnover());
    }

    @Test
    void testToString() {
        String bankName = "Test Bank";
        double rate = 5.5;
        int term = 24;
        boolean earlyRepayment = true;
        boolean creditLineIncrease = false;
        double maxBusinessLoanAmount = 200000.0;
        double minTurnover = 100000.0;

        BusinessCreditOffer offer = new BusinessCreditOffer(bankName, rate, term, earlyRepayment, creditLineIncrease, maxBusinessLoanAmount, minTurnover);

        String expectedString = "Банк: " + bankName + ", Ставка: " + rate +
                "%, Термін: " + term + " міс., Дострокове погашення: Так" +
                ", Збільшення кредитної лінії: Ні" +
                ", Максимальна сума бізнес-кредиту: " + maxBusinessLoanAmount +
                ", Мінімальний оборот: " + minTurnover;

        assertEquals(expectedString, offer.toString());
    }
}
