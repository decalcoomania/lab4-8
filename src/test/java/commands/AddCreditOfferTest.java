package commands;

import credit.CreditOfferManager;
import credit.PersonalCreditOffer;
import credit.BusinessCreditOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddCreditOfferTest {

    private CreditOfferManager manager;
    private Scanner scanner;
    private AddCreditOffer addCreditOffer;

    @BeforeEach
    public void setUp() {
        manager = mock(CreditOfferManager.class);
        scanner = mock(Scanner.class);
        addCreditOffer = new AddCreditOffer(manager, scanner);
    }

    @Test
    public void testAddPersonalCreditOffer() {
        // Мокування введення для особистої кредитної пропозиції
        when(scanner.nextInt()).thenReturn(1, 24, 1, 0, 10000); // Вибір пропозиції, строк, дострокове погашення, збільшення ліміту, максимальна сума
        when(scanner.nextDouble()).thenReturn(3.5); // Ставка
        when(scanner.nextLine()).thenReturn(""); // Пустий зчитувач для переходу між nextInt() та nextLine()
        when(scanner.nextLine()).thenReturn("Example Bank"); // Назва банку

        addCreditOffer.execute();

        ArgumentCaptor<PersonalCreditOffer> captor = ArgumentCaptor.forClass(PersonalCreditOffer.class);
        verify(manager).addCreditOffer(captor.capture());
        PersonalCreditOffer offer = captor.getValue();

        assertEquals("Example Bank", offer.getBankName());
        assertEquals(3.5, offer.getRate(), 0.001);
        assertEquals(24, offer.getTerm());
        assertTrue(offer.isEarlyRepayment());
        assertFalse(offer.isCreditLineIncrease());
        assertEquals(10000, offer.getMaxLoanAmount());
    }

    @Test
    public void testAddBusinessCreditOffer() {
        // Мокування введення для бізнес-кредитної пропозиції
        when(scanner.nextInt()).thenReturn(2, 36, 1, 50000); // Вибір пропозиції, строк, дострокове погашення, мінімальний обіг
        when(scanner.nextDouble()).thenReturn(4.5); // Ставка
        when(scanner.nextLine()).thenReturn(""); // Пустий зчитувач для переходу між nextInt() та nextLine()
        when(scanner.nextLine()).thenReturn("Business Bank"); // Назва банку

        addCreditOffer.execute();

        ArgumentCaptor<BusinessCreditOffer> captor = ArgumentCaptor.forClass(BusinessCreditOffer.class);
        verify(manager).addCreditOffer(captor.capture());
        BusinessCreditOffer offer = captor.getValue();

        assertEquals("Business Bank", offer.getBankName());
        assertEquals(4.5, offer.getRate(), 0.001);
        assertEquals(36, offer.getTerm());
        assertTrue(offer.isEarlyRepayment());
        assertEquals(50000, offer.getMinTurnover());
    }
}
