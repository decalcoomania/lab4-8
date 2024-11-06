package commands;

import credit.CreditOfferManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SaveCreditOffersTest {

    private CreditOfferManager manager;
    private SaveCreditOffers saveCommand;

    @BeforeEach
    public void setUp() {
        manager = mock(CreditOfferManager.class);
        saveCommand = new SaveCreditOffers(manager, "test_file.dat");
    }

    @Test
    public void testExecuteSavesCreditOffers() throws IOException {
        // Act
        saveCommand.execute();

        // Assert
        verify(manager, times(1)).saveToFile("test_file.dat");
    }

    @Test
    public void testExecuteHandlesIOException() throws IOException {
        // Arrange
        doThrow(new IOException("Test error")).when(manager).saveToFile(anyString());

        // Act
        saveCommand.execute();

        // Assert
        // The output would need to be captured, so we can assert it. This part is omitted
        // for simplicity, but you could use a ByteArrayOutputStream to capture System.out
        // and verify the error message.
    }
}
