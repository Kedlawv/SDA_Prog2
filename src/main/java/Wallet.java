public class Wallet {
    private long money;
    private Currency currency;


    public Wallet(Currency walletCurrency) {
        this.currency = walletCurrency;
    }

    public void add(double amount) {
        money += Math.round(amount * 100);
    }

    public void removeMoneyFromWallet(double amount){
        money -= Math.round(amount * 100);
    }


    public long getPln() {
        return money / 100;
    }

    public Currency getCurrency() {
        return currency;
    }
}
