package TDD_Wallet_Exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {

        LOGGER.info("Zaczynamy logowanie");

        Person person1 = new Person("John");
        Person person2 = new Person("Anna");
        Money pln100 = new Money(new BigDecimal(100),Currency.PLN);

        person1.receive(pln100);
        LOGGER.info("person1 received {}",pln100);
        person1.pay(person2,new Money(new BigDecimal(50),Currency.PLN));

        person1.pay(person2, new Money(new BigDecimal(50),Currency.GBP));

    }
}
