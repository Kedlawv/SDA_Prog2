package TDD_Wallet_Exercise;

import java.math.BigDecimal;

public class Money {
    BigDecimal amount;
    Currency currency;

    {
        currency = Currency.PLN;
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

    public void add(Money money){
        amount = this.amount.add(money.amount);
    }
}
