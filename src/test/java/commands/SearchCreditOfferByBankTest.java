package commands;

import credit.CreditOfferManager;
import credit.CreditOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SearchCreditOfferByBankTest {

    private CreditOfferManager manager;
    private SearchCreditOfferByBank searchCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        manager = mock(CreditOfferManager.class);
        // Імітуємо введення "Test Bank" для сканера
        Scanner scanner = new Scanner("Test Bank\n");
        searchCommand = new SearchCreditOfferByBank(manager, scanner);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testExecuteNoOffersFound() {
        // Arrange: Повертаємо порожній список, коли викликається пошук за банком
        when(manager.searchByBank("Test Bank")).thenReturn(new ArrayList<>());

        // Act
        searchCommand.execute();

        // Assert: Перевіряємо, що у виводі міститься повідомлення про відсутність пропозицій
        assertTrue(outputStream.toString().contains("Немає кредитних пропозицій із такою назвою банку: Test Bank"));
    }

    @Test
    public void testExecuteOffersFound() {
        // Arrange: Повертаємо список з одним кредитом для певного банку
        List<CreditOffer> offers = new ArrayList<>();
        CreditOffer offer = mock(CreditOffer.class);
        when(offer.toString()).thenReturn("Банк: Test Bank, Ставка: 5%, Термін: 12 міс.");
        offers.add(offer);

        when(manager.searchByBank("Test Bank")).thenReturn(offers);

        // Act
        searchCommand.execute();

        // Assert: Перевіряємо, що у виводі міститься інформація про знайдену пропозицію
        assertTrue(outputStream.toString().contains("Банк: Test Bank, Ставка: 5%, Термін: 12 міс."));
    }
}
