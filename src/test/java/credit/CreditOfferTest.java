package credit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreditOfferTest {

    private CreditOffer creditOffer;

    @BeforeEach
    public void setUp() {
        creditOffer = new PersonalCreditOffer("Example Bank", 3.5, 24, true, false, 10000);
    }

    @Test
    public void testGetBankName() {
        assertEquals("Example Bank", creditOffer.getBankName());
    }

    @Test
    public void testGetRate() {
        assertEquals(3.5, creditOffer.getRate(), 0.001); // Додано дельту для порівняння з плаваючою точкою
    }

    @Test
    public void testGetTerm() {
        assertEquals(24, creditOffer.getTerm());
    }

    @Test
    public void testIsEarlyRepayment() {
        assertTrue(creditOffer.isEarlyRepayment());
    }

    @Test
    public void testIsCreditLineIncrease() {
        assertFalse(creditOffer.isCreditLineIncrease());
    }

    @Test
    public void testToString() {
        String expected = "Банк: Example Bank, Ставка: 3.5%, Термін: 24 міс., Дострокове погашення: Так, Збільшення кредитної лінії: Ні";
        assertEquals(expected, creditOffer.toString());
    }
}
