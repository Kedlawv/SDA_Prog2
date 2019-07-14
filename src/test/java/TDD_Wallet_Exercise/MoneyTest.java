package TDD_Wallet_Exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    Money money;
    @BeforeEach
    public void createMoney(){
        money = new Money();
    }

    @Test
    public void trueIfNewMoneyHasCorrectDefaultValues(){

        BigDecimal expectedValue = new BigDecimal(0);
        Currency expectedCurrency = Currency.PLN;

        assertTrue(money.amount.equals(expectedValue));
        assertEquals(expectedCurrency,money.currency);
    }

    @ParameterizedTest
    @ValueSource(doubles  = {20,25,50,40.33,5000.23525})
    public void trueIfHasEnoughMoney(double arg){
        money.add(new Money(new BigDecimal(arg)));

        Money moneyRequested = new Money(new BigDecimal(20));

        assertTrue(money.hasEnoughMoney(moneyRequested));

    }

    @ParameterizedTest
    @ValueSource(doubles  = {2,5,0,-20.33})
    public void falseIfHasNotEnoughMoney(double arg){
        money.add(new Money(new BigDecimal(arg)));

        Money moneyRequested = new Money(new BigDecimal(20));

        assertFalse(money.hasEnoughMoney(moneyRequested));

    }

}