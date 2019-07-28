package TDD_Wallet_Exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person personOne;
    Person personTwo;

    @BeforeEach
    public void createPerson(){
        personOne = new Person("John");
        personTwo = new Person("Anna");
    }

    @Test
    public void trueIfPersonHasWallet(){
        boolean expectedHasWallet = personOne.wallet != null;

        assertEquals(personOne.hasWallet(),expectedHasWallet);
    }

    @Test
    public void falseIfPersonWalletIsNull(){
        personOne.wallet = null;

        assertNull(personOne.wallet);
    }

    @Test
    public void trueIfPayAddsCorrect() throws NotMatchingCurrencyException {
        Money expectedMoney = new Money(new BigDecimal(20.56),Currency.PLN);

        personOne.wallet.deposit(expectedMoney);
        personOne.pay(personTwo,expectedMoney);

        assertEquals(expectedMoney,personTwo.wallet.moneyMap.get(Currency.PLN));
    }

    @Test
    public void trueIfSubtractsCorrectFromPayer() throws NotMatchingCurrencyException {
        Money startingMoney = new Money(new BigDecimal(11.99),Currency.PLN);
        Money expectedMoney = new Money(BigDecimal.ZERO,Currency.PLN);
        personOne.wallet.deposit(startingMoney);

        personOne.pay(personTwo,startingMoney);

        assertEquals(expectedMoney, personOne.wallet.moneyMap.get(Currency.PLN));

    }


}