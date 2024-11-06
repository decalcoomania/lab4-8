package credit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.io.Serializable;

public class BusinessCreditOffer extends CreditOffer implements Serializable {
    //private static final long serialVersionUID = 1L;
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR"); // Ініціалізація логера

    private double maxBusinessLoanAmount; // Максимальна сума кредиту
    private double minTurnover; // Мінімальний обіг

    // Основний конструктор з усіма параметрами
    public BusinessCreditOffer(String bankName, double rate, int term, boolean earlyRepayment, boolean creditLineIncrease, double maxBusinessLoanAmount, double minTurnover) {
        super(bankName, rate, term, earlyRepayment, creditLineIncrease);
        this.maxBusinessLoanAmount = maxBusinessLoanAmount;
        this.minTurnover = minTurnover;
        fileLogger.info("Створено нову бізнес-кредитну пропозицію для банку: {}", bankName); // Логування дії
    }

    public double getMaxBusinessLoanAmount() {
        fileLogger.debug("Отримано максимальну суму кредиту: {}", maxBusinessLoanAmount); // Логування дії
        return maxBusinessLoanAmount;
    }

    public double getMinTurnover() {
        fileLogger.debug("Отримано мінімальний обіг: {}", minTurnover); // Логування дії
        return minTurnover;
    }

    @Override
    public String toString() {
        String result = "Банк: " + getBankName() + ", Ставка: " + getRate() +
                "%, Термін: " + getTerm() + " міс., Дострокове погашення: " +
                (isEarlyRepayment() ? "Так" : "Ні") +
                ", Збільшення кредитної лінії: " + (isCreditLineIncrease() ? "Так" : "Ні") +
                ", Максимальна сума бізнес-кредиту: " + maxBusinessLoanAmount +
                ", Мінімальний оборот: " + minTurnover;
        fileLogger.info("Виведення пропозиції на основі бізнес-кредиту: {}", result); // Логування дії
        return result;
    }

    // Метод для обробки помилок
    public void processError(Exception e) {
        fileLogger.error("Сталася критична помилка: {}", e.getMessage(), e); // Логування помилки
    }
}
