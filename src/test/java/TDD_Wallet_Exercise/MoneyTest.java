package TDD_Wallet_Exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    Money money;

    @BeforeEach
    public void createMoney() {
        money = new Money();
    }

    @Test
    public void trueIfNewMoneyHasCorrectDefaultValues() {

        BigDecimal expectedValue = new BigDecimal(0);
        Currency expectedCurrency = Currency.PLN;

        assertTrue(money.amount.equals(expectedValue));
        assertEquals(expectedCurrency, money.getCurrency());
    }

    @ParameterizedTest
    @ValueSource(doubles = {20, 25, 50, 40.33, 5000.23525})
    public void trueIfHasEnoughMoney(double arg) {
        money.add(new Money(new BigDecimal(arg)));

        Money moneyRequested = new Money(new BigDecimal(20));

        assertTrue(money.hasEnoughMoney(moneyRequested));

    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 5, 0, -20.33})
    public void falseIfHasNotEnoughMoney(double arg) {
        money.add(new Money(new BigDecimal(arg)));

        Money moneyRequested = new Money(new BigDecimal(20));

        assertFalse(money.hasEnoughMoney(moneyRequested));

    }

    @ParameterizedTest
    @ValueSource(doubles = {20.99, 14.95, 1, Double.MAX_VALUE, Double.MIN_VALUE})
    public void trueIfAddsCorrectAmountOfMoneyToNewMoney(double paramValue) {
        Money expectedMoney = new Money(new BigDecimal(paramValue));
        System.out.println(expectedMoney);

        money.add(new Money(new BigDecimal(paramValue)));

        assertEquals(expectedMoney, money);

    }

    @Test
    public void trueIfAddsMultipleCorrect() {
        //Arrange
        BigDecimal[] bigDecArray = new BigDecimal[]
                {new BigDecimal(1.50), new BigDecimal(20.99), new BigDecimal(1000.56)};
        BigDecimal expectedValue;
        expectedValue = Arrays.stream(bigDecArray).
                reduce(new BigDecimal(0), (a, b) -> a = a.add(b));

        //Act
        for (BigDecimal value : bigDecArray) {
            money.add(new Money(value));
        }

        //Assert
        assertEquals(new Money(expectedValue), money);
    }

    @ParameterizedTest
    @ValueSource (doubles = {1.00, 2.99, 2134.1235, Double.MAX_VALUE, Double.MIN_VALUE})
    public void trueIfSubtractsCorrectValue(double paramValue){
        money.add(new Money(new BigDecimal(paramValue)));
        Money expectedMoney = new Money(new BigDecimal(0));

//        System.out.println("trueIfSubtractsCorrectValue()______________________");
//        System.out.println("money: " + money + " \nexpectedMoney: " + expectedMoney);

        money.subtract(new Money(new BigDecimal(paramValue)));
//        System.out.println("Money after subtraction: " + money + "\n");

        assertEquals(expectedMoney, money);
    }

    @Test
    public void trueIfSubtractsMultipleCorrect() {
        //Arrange
        BigDecimal[] bigDecArray = new BigDecimal[]
                {new BigDecimal(1.50), new BigDecimal(20.99), new BigDecimal(1000.56)};
        BigDecimal sumValue = Arrays.stream(bigDecArray).
                reduce(new BigDecimal(0),(a, b) -> a = a.add(b));
        money = new Money(sumValue);
        Money expectedMoney = new Money(new BigDecimal(0));

        //Act
        for (BigDecimal value : bigDecArray) {
            money.subtract(new Money(value));
        }

        //Assert
        assertEquals(expectedMoney, money);
    }

}