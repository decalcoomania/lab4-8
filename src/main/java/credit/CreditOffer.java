package credit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;


import java.io.Serializable;

public class CreditOffer implements Serializable {
    //private static final long serialVersionUID = 1L;
    //private static final Logger logger = LogManager.getLogger(CreditOffer.class); // Ініціалізація логера
    private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");

    private String bankName;
    private double rate;
    private int term;
    private boolean earlyRepayment;
    private boolean creditLineIncrease;

    public CreditOffer(String bankName, double rate, int term, boolean earlyRepayment, boolean creditLineIncrease) {
        this.bankName = bankName;
        this.rate = rate;
        this.term = term;
        this.earlyRepayment = earlyRepayment;
        this.creditLineIncrease = creditLineIncrease;
        fileLogger.info("Створено нову кредитну пропозицію для банку: {}", bankName); // Логування дії
    }

    public String getBankName() {
        fileLogger.debug("Отримано назву банку: {}", bankName); // Логування дії
        return bankName;
    }

    public double getRate() {
        fileLogger.debug("Отримано ставку: {}", rate); // Логування дії
        return rate;
    }

    public int getTerm() {
        fileLogger.debug("Отримано термін кредиту: {} міс.", term); // Логування дії
        return term;
    }

    public boolean isEarlyRepayment() {
        fileLogger.debug("Перевірка на можливість дострокового погашення: {}", earlyRepayment); // Логування дії
        return earlyRepayment;
    }

    public boolean isCreditLineIncrease() {
        fileLogger.debug("Перевірка на можливість збільшення кредитної лінії: {}", creditLineIncrease); // Логування дії
        return creditLineIncrease;
    }

    // Метод для обробки помилок
    public void processError(Exception e) {
        fileLogger.error("Сталася критична помилка: {}", e.getMessage(), e); // Логування помилки
    }

    @Override
    public String toString() {
        String result = "Банк: " + bankName + ", Ставка: " + rate +
                "%, Термін: " + term + " міс., Дострокове погашення: " +
                (earlyRepayment ? "Так" : "Ні") +
                ", Збільшення кредитної лінії: " + (creditLineIncrease ? "Так" : "Ні");
        fileLogger.info("Виведення пропозиції на основі кредиту: {}", result); // Логування дії
        return result;
    }

}
