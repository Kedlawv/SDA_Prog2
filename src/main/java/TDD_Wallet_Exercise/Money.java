package TDD_Wallet_Exercise;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private BigDecimal amount;
    private Currency currency;

    {
        currency = Currency.PLN; // working with one currency at the moment todo multiple currencies
    }

    public Money() {
        amount = new BigDecimal(0);
    }

    public Money(BigDecimal amount){
        this.amount = amount;
    }

    public Boolean hasEnoughMoney(Money money) {
        return this.amount.compareTo(money.amount) >= 0;
    }

    public Money add(Money money){

        this.amount = this.amount.add(money.amount);
        return this;
    }

    public Money subtract(Money money) {
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
