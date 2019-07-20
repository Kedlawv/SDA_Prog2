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
        personOne = new Person();
        personTwo = new Person();
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
    public void trueIfPayAddsCorrect(){
        Money expectedMoney = new Money(new BigDecimal(20.56));
        personOne.pay(personTwo,expectedMoney);

        assertEquals(personTwo.wallet.getBalance(), expectedMoney);
    }

    @Test
    public void trueIfSubtractsCorrectFromPayer(){
        Money startingMoney = new Money(new BigDecimal(11.99));
        Money expectedMoney = new Money(BigDecimal.ZERO);
        personOne.wallet.deposit(startingMoney);

        personOne.pay(personTwo,startingMoney);

        assertEquals(expectedMoney, personOne.wallet.getBalance());

    }

}