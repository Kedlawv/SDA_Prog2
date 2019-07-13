package WalletExercise;

import java.math.BigDecimal;

public class Money {
    BigDecimal amount;
    Currency currency;

    public Money(BigDecimal ammount, Currency currency) {
        this.amount = ammount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }



    public Currency getCurrency() {
        return currency;
    }


    public Money add(Money p) {
        if (!(this.getCurrency() == p.getCurrency())) {
            return null;

        }
        amount = this.getAmount().add(p.getAmount());
        return this;

    }



}
