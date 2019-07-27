package TDD_Wallet_Exercise;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private BigDecimal amount;
    private Currency currency;


    public Money(BigDecimal amount,Currency currency){
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    public Boolean hasEnoughMoney(Money money) throws NotMatchingCurrencyException {
        checkCurrency(money);
        return this.amount.compareTo(money.amount) >= 0;
    }

    private void checkCurrency(Money money) throws NotMatchingCurrencyException {
        if(!(this.currency == money.currency)) {
            throw new NotMatchingCurrencyException();
        }
    }

    public Money add(Money money) throws NotMatchingCurrencyException {

        checkCurrency(money);

        this.amount = this.amount.add(money.amount);


        return this;
    }

    public Money subtract(Money money) throws InsuffitientBalansException, NotMatchingCurrencyException {
        checkCurrency(money);

        if(!hasEnoughMoney(money)){
            throw new InsuffitientBalansException();
        }
        this.amount = this.amount.subtract(money.amount);
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return (this.amount.compareTo(money.amount)) == 0 &&
                this.currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
