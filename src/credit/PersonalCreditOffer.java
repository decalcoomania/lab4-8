package credit;

public class PersonalCreditOffer extends CreditOffer {
    public PersonalCreditOffer(String bankName, double interestRate, int termMonths, boolean earlyRepaymentAllowed, boolean creditLineIncreaseAllowed) {
        super(bankName, interestRate, termMonths, earlyRepaymentAllowed, creditLineIncreaseAllowed);
    }
}