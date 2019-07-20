package TDD_Wallet_Exercise;

public class Person {

    Wallet wallet;

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
        }


    }

    public void receive(Money money){
        wallet.deposit(money);
    }
}
