package app;

import commands.*;
import credit.CreditOfferManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoanAppTest {
    private static final Logger logger = LoggerFactory.getLogger(LoanAppTest.class);

    private CreditOfferManager mockManager;
    private Scanner mockScanner;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() throws IOException, ClassNotFoundException {
        mockManager = mock(CreditOfferManager.class);
        mockScanner = mock(Scanner.class);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Simulate successful loading from file
        doNothing().when(mockManager).loadFromFile(anyString());

        // Set up the mocked scanner and manager in LoanApp
        LoanApp.setScanner(mockScanner);
        LoanApp.setManager(mockManager);
    }

    @Disabled
    @Test
    public void testMainMethodExit() throws Exception {
        when(mockScanner.nextInt()).thenReturn(8);  // User selects exit
        when(mockScanner.nextLine()).thenReturn("");

        LoanApp.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Credit offers loaded from file."));
        assertTrue(output.contains("Exiting."));
    }

    @Disabled
    @Test
    public void testMainMethodInvalidChoiceAndRetry() throws Exception {
        when(mockScanner.nextInt()).thenReturn(9, 1, 8); // Invalid choice, valid choice, then exit
        when(mockScanner.nextLine()).thenReturn(""); // Clear buffer

        LoanApp.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Invalid choice.")); // First invalid choice
        assertTrue(output.contains("Credit offers loaded from file."));
        assertTrue(output.contains("Exiting."));
    }

    @Disabled
    @Test
    public void testInvalidInputType() {
        when(mockScanner.nextInt()).thenThrow(new RuntimeException("Invalid input"));
        when(mockScanner.nextLine()).thenReturn("");

        LoanApp.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Invalid input type."));
    }

    @Disabled
    @Test
    public void testLoadCreditOffersIOException() throws Exception {
        doThrow(new IOException("File not found")).when(mockManager).loadFromFile(anyString());

        LoanApp.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Error loading: File not found"));
    }

    @Test
    public void testDisplayMenu() {
        LoanApp.displayMenu();
        String output = outputStream.toString();
        assertTrue(output.contains("Menu:"));
        assertTrue(output.contains("1. Add credit offer"));
        assertTrue(output.contains("8. Exit"));
    }

    @Test
    public void testGetUserChoice() {
        when(mockScanner.nextInt()).thenReturn(1);
        int choice = LoanApp.getUserChoice();
        assertEquals(1, choice);
    }

    @Test
    public void testGetUserChoiceInvalid() {
        when(mockScanner.nextInt()).thenThrow(new RuntimeException("Invalid input"));
        when(mockScanner.nextLine()).thenReturn("");

        int choice = LoanApp.getUserChoice();
        assertEquals(-1, choice);
    }

    @Test
    public void testCreateCommandValidChoices() {
        assertNotNull(LoanApp.createCommand(1)); // AddCreditOffer
        assertNotNull(LoanApp.createCommand(2)); // SearchCreditOfferByRate
        assertNotNull(LoanApp.createCommand(3)); // SearchCreditOfferByBank
        assertNotNull(LoanApp.createCommand(4)); // SaveCreditOffers
        assertNotNull(LoanApp.createCommand(5)); // DeleteCreditOfferByIndex
        assertNotNull(LoanApp.createCommand(6)); // DeleteCreditOfferByBank
        assertNotNull(LoanApp.createCommand(7)); // DisplayCreditOffers
    }

    @Test
    public void testCreateCommandInvalidChoice() {
        assertNull(LoanApp.createCommand(99)); // Invalid choice
    }

    @Test
    public void testGetUserChoiceValidThenInvalid() {
        when(mockScanner.nextInt()).thenReturn(2).thenThrow(new RuntimeException("Invalid input"));
        when(mockScanner.nextLine()).thenReturn("");

        int firstChoice = LoanApp.getUserChoice();
        int secondChoice = LoanApp.getUserChoice();

        assertEquals(2, firstChoice);
        assertEquals(-1, secondChoice);
    }

    @Test
    public void testCreateCommandExitChoice() {
        assertNull(LoanApp.createCommand(8)); // Exit command should return null
    }

    @Test
    public void testCreateCommandNullCheck() {
        Command command = LoanApp.createCommand(0);
        assertNull(command); // Ensure null is returned for out-of-range input
    }

    @Test
    public void testGetUserChoiceWithEdgeValues() {
        when(mockScanner.nextInt()).thenReturn(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertEquals(Integer.MIN_VALUE, LoanApp.getUserChoice());
        assertEquals(Integer.MAX_VALUE, LoanApp.getUserChoice());
    }
}
