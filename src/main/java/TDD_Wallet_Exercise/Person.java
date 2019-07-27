package TDD_Wallet_Exercise;

public class Person {

    Wallet wallet;
    //todo check "none value"

    public Person(){
        wallet = new Wallet();
    }

    public boolean hasWallet() {
        if(wallet != null){
            return true;
        }else{
            return false;
        }
    }

    public void pay(Person recipient, Money money){
        try {
            this.wallet.withdraw(money);
            recipient.receive(money);
        }catch (InsuffitientBalansException e){
            System.out.println("Insufficient funds");
        } catch (NotMatchingCurrencyException e) {
            System.out.println("Currencies do not match");
            e.printStackTrace();
        }


    }

    public void receive(Money money){
        try {
            wallet.deposit(money);
        } catch (NotMatchingCurrencyException e) {
            System.out.println("Currencies do not match");
            e.printStackTrace();
        }
    }
}
