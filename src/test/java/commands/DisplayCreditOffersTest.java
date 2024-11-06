package commands;

import credit.CreditOfferManager;
import credit.CreditOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DisplayCreditOffersTest {

    private CreditOfferManager manager;
    private DisplayCreditOffers displayCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        manager = mock(CreditOfferManager.class);
        displayCommand = new DisplayCreditOffers(manager);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testExecuteDisplaysNoOffersMessage() {
        // Arrange
        when(manager.getCreditOffers()).thenReturn(new ArrayList<>());

        // Act
        displayCommand.execute();

        // Assert
        String expectedOutput = "Немає доступних кредитних пропозицій.\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testExecuteDisplaysCreditOffers() {
        // Arrange
        List<CreditOffer> offers = new ArrayList<>();
        CreditOffer offer1 = mock(CreditOffer.class);
        CreditOffer offer2 = mock(CreditOffer.class);
        offers.add(offer1);
        offers.add(offer2);
        when(manager.getCreditOffers()).thenReturn(offers);
        when(offer1.toString()).thenReturn("Кредитна пропозиція 1");
        when(offer2.toString()).thenReturn("Кредитна пропозиція 2");

        // Act
        displayCommand.execute();

        // Assert
        String expectedOutput = "0. Кредитна пропозиція 1\n" +
                "1. Кредитна пропозиція 2\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
