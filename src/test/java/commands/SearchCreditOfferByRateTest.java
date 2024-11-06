package commands;

import credit.CreditOfferManager;
import credit.CreditOffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SearchCreditOfferByRateTest {

    private CreditOfferManager manager;
    private SearchCreditOfferByRate searchCommand;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        manager = mock(CreditOfferManager.class);
        originalOut = System.out; // Зберігаємо початковий потік
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut); // Відновлюємо початковий потік
    }

    @Test
    public void testExecuteNoOffersFound() {
        // Arrange
        when(manager.searchByRate(anyDouble())).thenReturn(new ArrayList<>());
        Scanner scanner = new Scanner("5.0"); // Емуляція вводу користувача
        searchCommand = new SearchCreditOfferByRate(manager, scanner);

        // Act
        searchCommand.execute();

        // Assert
        assertTrue(outputStream.toString().contains("Немає кредитних пропозицій із такою ставкою."));
    }

    @Test
    public void testExecuteOffersFound() {
        // Arrange
        List<CreditOffer> offers = new ArrayList<>();
        CreditOffer offer = mock(CreditOffer.class);
        when(offer.toString()).thenReturn("Банк: Test Bank, Ставка: 5%, Термін: 12 міс.");
        offers.add(offer);
        when(manager.searchByRate(anyDouble())).thenReturn(offers);
        Scanner scanner = new Scanner("5.0"); // Емуляція вводу користувача
        searchCommand = new SearchCreditOfferByRate(manager, scanner);

        // Act
        searchCommand.execute();

        // Assert
        assertTrue(outputStream.toString().contains("Банк: Test Bank, Ставка: 5%, Термін: 12 міс."));
    }

    @Test
    public void testExecuteInvalidInput() {
        // Arrange
        Scanner scanner = new Scanner("invalid input"); // Емуляція некоректного вводу
        searchCommand = new SearchCreditOfferByRate(manager, scanner);

        // Act
        searchCommand.execute();

        // Assert
        assertTrue(outputStream.toString().contains("Будь ласка, введіть дійсне число."));
    }

    @Test
    public void testExecuteMultipleOffersFound() {
        // Arrange
        List<CreditOffer> offers = new ArrayList<>();
        CreditOffer offer1 = mock(CreditOffer.class);
        CreditOffer offer2 = mock(CreditOffer.class);
        when(offer1.toString()).thenReturn("Банк: Test Bank 1, Ставка: 5%, Термін: 12 міс.");
        when(offer2.toString()).thenReturn("Банк: Test Bank 2, Ставка: 4.5%, Термін: 24 міс.");
        offers.add(offer1);
        offers.add(offer2);
        when(manager.searchByRate(anyDouble())).thenReturn(offers);
        Scanner scanner = new Scanner("5.0"); // Емуляція вводу користувача
        searchCommand = new SearchCreditOfferByRate(manager, scanner);

        // Act
        searchCommand.execute();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Банк: Test Bank 1, Ставка: 5%, Термін: 12 міс."));
        assertTrue(output.contains("Банк: Test Bank 2, Ставка: 4.5%, Термін: 24 міс."));
    }

    @Test
    public void testExecuteNegativeRate() {
        // Arrange
        Scanner scanner = new Scanner("-5.0\n5.0"); // Спершу вводимо від'ємну ставку, потім коректну
        searchCommand = new SearchCreditOfferByRate(manager, scanner);

        // Act
        searchCommand.execute();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Ставка повинна бути невід'ємною. Спробуйте ще раз."));
    }

    @Test
    public void testExecuteRetryAfterInvalidInput() {
        // Arrange
        when(manager.searchByRate(anyDouble())).thenReturn(new ArrayList<>());
        Scanner scanner = new Scanner("invalid input\n5.0"); // Некоректний ввід, потім коректний
        searchCommand = new SearchCreditOfferByRate(manager, scanner);

        // Act
        searchCommand.execute();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Будь ласка, введіть дійсне число."));
        assertTrue(output.contains("Немає кредитних пропозицій із такою ставкою."));
    }

    @Test
    public void testExecuteZeroRate() {
        // Arrange
        List<CreditOffer> offers = new ArrayList<>();
        CreditOffer offer = mock(CreditOffer.class);
        when(offer.toString()).thenReturn("Банк: Zero Rate Bank, Ставка: 0%, Термін: 6 міс.");
        offers.add(offer);
        when(manager.searchByRate(0.0)).thenReturn(offers);
        Scanner scanner = new Scanner("0.0");
        searchCommand = new SearchCreditOfferByRate(manager, scanner);

        // Act
        searchCommand.execute();

        // Assert
        assertTrue(outputStream.toString().contains("Банк: Zero Rate Bank, Ставка: 0%, Термін: 6 міс."));
    }
}
