package credit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.Marker;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreditOfferManager {
   // private static final Logger logger = LogManager.getLogger(CreditOfferManager.class); // Ініціалізація логера
   private static final Logger fileLogger = LogManager.getLogger("FileOnlyLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");
    private static final Marker ERROR_MARKER = MarkerManager.getMarker("ERROR");
    private List<CreditOffer> creditOffers = new ArrayList<>();

    public void addCreditOffer(CreditOffer offer) {
        creditOffers.add(offer);
        fileLogger.info("Додано нову кредитну пропозицію від банку: {}", offer.getBankName()); // Логування додавання
    }

    public void deleteCreditOfferByIndex(int index) {
        if (index >= 0 && index < creditOffers.size()) {
            creditOffers.remove(index);
            fileLogger.info("Кредитну пропозицію з індексом {} було видалено", index); // Логування видалення
        } else {
            fileLogger.warn("Спроба видалення за неправильним індексом: {}", index); // Логування помилки
            System.out.println("Індекс поза межами списку.");
        }
    }

    public void deleteCreditOfferByBank(String bankName) {
        List<CreditOffer> toRemove = creditOffers.stream()
                .filter(offer -> offer.getBankName().equalsIgnoreCase(bankName))
                .collect(Collectors.toList());

        if (!toRemove.isEmpty()) {
            creditOffers.removeAll(toRemove);
            fileLogger.info("Видалено всі кредитні пропозиції для банку: {}", bankName); // Логування видалення
        } else {
            fileLogger.warn("Кредитні пропозиції для банку '{}' не знайдено", bankName); // Логування помилки
        }
    }

    public List<CreditOffer> getCreditOffers() {
        fileLogger.debug("Отримано список всіх кредитних пропозицій."); // Логування отримання списку
        return creditOffers;
    }

    public List<CreditOffer> searchByRate(double rate) {
        fileLogger.info("Пошук кредитних пропозицій за ставкою: {}", rate); // Логування пошуку
        return creditOffers.stream()
                .filter(offer -> offer.getRate() == rate)
                .collect(Collectors.toList());
    }

    public List<CreditOffer> searchByBank(String bankName) {
        fileLogger.info("Пошук кредитних пропозицій для банку: {}", bankName); // Логування пошуку
        return creditOffers.stream()
                .filter(offer -> offer.getBankName().equalsIgnoreCase(bankName))
                .collect(Collectors.toList());
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(creditOffers);
            fileLogger.info("Збережено список кредитних пропозицій до файлу: {}", filename); // Логування збереження
        } catch (IOException e) {
            fileLogger.error("Помилка при збереженні даних у файл: {}", filename, e); // Логування помилки
            throw e;  // Проброс винятку
        }
    }
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            creditOffers = (List<CreditOffer>) in.readObject();
            fileLogger.info("Завантажено список кредитних пропозицій з файлу: {}", filename); // Логування завантаження
        } catch (IOException | ClassNotFoundException e) {
            fileLogger.error("Помилка при завантаженні даних з файлу: {}", filename, e); // Логування помилки
            throw e;  // Проброс винятку
        }
    }

    /*public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            creditOffers = (List<CreditOffer>) in.readObject();
            fileLogger.info("Завантажено список кредитних пропозицій з файлу: {}", filename); // Логування завантаження
        } catch (IOException | ClassNotFoundException e) {
            fileLogger.error("Помилка при завантаженні даних з файлу: {}", filename, e); // Логування помилки
            throw e;  // Проброс винятку
        }
    }*/
}
