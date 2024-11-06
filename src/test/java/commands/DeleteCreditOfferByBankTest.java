package commands;

import credit.CreditOfferManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.mockito.Mockito.*;

public class DeleteCreditOfferByBankTest {

    private CreditOfferManager manager;
    private Scanner scanner;
    private DeleteCreditOfferByBank deleteCommand;

    @BeforeEach
    public void setUp() {
        manager = mock(CreditOfferManager.class);
        scanner = mock(Scanner.class);
        deleteCommand = new DeleteCreditOfferByBank(manager, scanner);
    }

    @Test
    public void testExecuteDeletesOffers() {
        // Arrange
        String bankName = "Test Bank";
        when(scanner.nextLine()).thenReturn(bankName);

        // Act
        deleteCommand.execute();

        // Assert
        verify(manager).deleteCreditOfferByBank(bankName);
        // Additional verification for output can be added using a print stream redirection if necessary
    }
}
