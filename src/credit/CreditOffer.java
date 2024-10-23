package credit;

import java.io.Serializable;

public class CreditOffer implements Serializable {
    protected String bankName;
    protected double interestRate;
    protected int termMonths;
    protected boolean earlyRepaymentAllowed;
    protected boolean creditLineIncreaseAllowed;

    public CreditOffer(String bankName, double interestRate, int termMonths, boolean earlyRepaymentAllowed, boolean creditLineIncreaseAllowed) {
        this.bankName = bankName;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
        this.creditLineIncreaseAllowed = creditLineIncreaseAllowed;
    }

    public String getBankName() {
        return bankName;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public boolean isEarlyRepaymentAllowed() {
        return earlyRepaymentAllowed;
    }

    public boolean isCreditLineIncreaseAllowed() {
        return creditLineIncreaseAllowed;
    }

    @Override
    public String toString() {
        return String.format("Банк: %s, Ставка: %.2f%%, Термін: %d міс., Дострокове погашення: %s, Збільшення кредитної лінії: %s",
                bankName, interestRate, termMonths, earlyRepaymentAllowed ? "так" : "ні", creditLineIncreaseAllowed ? "так" : "ні");
    }
}
