import java.util.HashMap;
import java.util.Map;

public class Person {

    private Map<Currency, Wallet> wallets;

    {
        wallets = new HashMap<>();
    }

    public Person(Wallet... wallets) {
        for (Wallet wallet : wallets) {
            addWallet(wallet);
        }
    }

    public void giveMoney(Person person, double amount, Currency currency) {
        if (person != null) {
            Wallet wallet = wallets.get(currency);
            wallet.removeMoneyFromWallet(amount);
            person.acceptMoney(amount, currency);


        }
    }

    public void acceptMoney(double amount, Currency currency) {
        Wallet wallet = wallets.get(currency);
        wallet.add(amount);
    }

    public void addWallet(Wallet wallet){
        this.wallets.put(wallet.getCurrency(), wallet);
    }


}
