package credit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import java.io.Serializable;

public class PersonalCreditOffer extends CreditOffer implements Serializable {
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR"); // Ініціалізація логера
   // private static final long serialVersionUID = 1L;

    private double maxLoanAmount;

    public PersonalCreditOffer(String bankName, double rate, int term, boolean earlyRepayment, boolean creditLineIncrease, double maxLoanAmount) {
        super(bankName, rate, term, earlyRepayment, creditLineIncrease);
        this.maxLoanAmount = maxLoanAmount;
        fileLogger.info("Створено нову персональну кредитну пропозицію від банку: {}", bankName); // Логування створення пропозиції
    }

    public double getMaxLoanAmount() {
        fileLogger.debug("Отримано максимальну суму кредиту: {}", maxLoanAmount); // Логування доступу до значення
        return maxLoanAmount;
    }

    @Override
    public String toString() {
        String result = "Банк: " + getBankName() + ", Ставка: " + getRate() +
                "%, Термін: " + getTerm() + " міс., Дострокове погашення: " +
                (isEarlyRepayment() ? "Так" : "Ні") +
                ", Збільшення кредитної лінії: " + (isCreditLineIncrease() ? "Так" : "Ні") +
                ", Максимальна сума кредиту: " + maxLoanAmount;

        fileLogger.debug("ДоString метод повернув: {}", result); // Логування результату toString
        return result;
    }
}
