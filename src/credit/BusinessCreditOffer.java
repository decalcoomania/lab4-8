package credit;

public class BusinessCreditOffer extends CreditOffer {
    public BusinessCreditOffer(String bankName, double interestRate, int termMonths, boolean earlyRepaymentAllowed, boolean creditLineIncreaseAllowed) {
        super(bankName, interestRate, termMonths, earlyRepaymentAllowed, creditLineIncreaseAllowed);
    }
}