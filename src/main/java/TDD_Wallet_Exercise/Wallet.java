package TDD_Wallet_Exercise;

public class Wallet {

    Money money;

    {
        money = new Money();
    }

    public Money getMoney() {
        return money;
    }

    public void deposit(Money money) {
        this.money.add(money);
    }

    public void withdraw(Money money){
        this.money.subtract(money);
    }

    public Money getBalance() {
        return money;
    }
}
