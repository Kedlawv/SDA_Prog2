package TDD_Wallet_Exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Person {

    Wallet wallet;
    String name;
    List<String> items;
    List<Offer> forsale;
    List<Offer> toBuy;
    private static Logger LOGGER = LoggerFactory.getLogger(Person.class);
    //todo check "none value method"

    public Person(String name){
        wallet = new Wallet();
        this.name = name;
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
            LOGGER.error("Insufficient funds");
        } catch (NotMatchingCurrencyException e) {
            LOGGER.error("Currencies do not match");
            e.printStackTrace();
        }


    }

    public void receive(Money money){
        try {
            wallet.deposit(money);
            LOGGER.info("Deposited {} id: {}", money, this);
        } catch (NotMatchingCurrencyException e) {
            LOGGER.error("Currencies do not match");
            e.printStackTrace();
        }
    }
}
