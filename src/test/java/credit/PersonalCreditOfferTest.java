package credit;

import static org.junit.jupiter.api.Assertions.*; // Use only JUnit 5 assertions
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonalCreditOfferTest {

    private PersonalCreditOffer personalCreditOffer;

    @BeforeEach
    public void setUp() {
        personalCreditOffer = new PersonalCreditOffer("Example Bank", 3.5, 24, true, false, 10000);
    }

    @Test
    public void testGetBankName() {
        assertEquals("Example Bank", personalCreditOffer.getBankName());
    }

    @Test
    public void testGetRate() {
        assertEquals(3.5, personalCreditOffer.getRate(), 0.001); // Use delta for floating-point comparison
    }

    @Test
    public void testGetTerm() {
        assertEquals(24, personalCreditOffer.getTerm());
    }

    @Test
    public void testIsEarlyRepayment() {
        assertTrue(personalCreditOffer.isEarlyRepayment());
    }

    @Test
    public void testIsCreditLineIncrease() {
        assertFalse(personalCreditOffer.isCreditLineIncrease());
    }

    @Test
    public void testToString() {
        String expected = "Банк: Example Bank, Ставка: 3.5%, Термін: 24 міс., Дострокове погашення: Так, Збільшення кредитної лінії: Ні, Максимальна сума кредиту: 10000.0";
        assertEquals(expected, personalCreditOffer.toString());
    }

    @Test
    public void testGetMaxLoanAmount() {
        assertEquals(10000, personalCreditOffer.getMaxLoanAmount());
    }
}
