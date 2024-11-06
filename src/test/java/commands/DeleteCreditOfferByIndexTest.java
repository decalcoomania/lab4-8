package commands;

import credit.CreditOfferManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.mockito.Mockito.*;

public class DeleteCreditOfferByIndexTest {

    private CreditOfferManager manager;
    private Scanner scanner;
    private DeleteCreditOfferByIndex deleteCommand;

    @BeforeEach
    public void setUp() {
        manager = mock(CreditOfferManager.class);
        scanner = mock(Scanner.class);
        deleteCommand = new DeleteCreditOfferByIndex(manager, scanner);
    }

    @Test
    public void testExecuteDeletesOffer() {
        // Arrange
        int index = 1;
        when(scanner.nextInt()).thenReturn(index);

        // Act
        deleteCommand.execute();

        // Assert
        verify(manager).deleteCreditOfferByIndex(index);
        // Additional verification for output can be added if needed
    }
}
