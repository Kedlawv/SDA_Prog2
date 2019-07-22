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
        money = new Money(new BigDecimal(0),Currency.PLN);
    }

    @Test
    public void trueIfNewMoneyHasCorrectDefaultValues() {

        BigDecimal expectedValue = new BigDecimal(0);
        Currency expectedCurrency = Currency.PLN;

        assertTrue(money.getAmount().equals(expectedValue));
        assertEquals(money.getCurrency(), expectedCurrency);
    }

    @Test
    public void trueIfEqualsForSameMoney(){
        Money money1 = new Money(new BigDecimal(20.99),Currency.PLN);
        Money money2 = new Money(new BigDecimal(20.99),Currency.PLN);

        assertEquals(money1,money2);

    }

    @Test
    public void falseIfNotEqualsForDifferentMoney(){
        Money money1 = new Money(new BigDecimal(20.99),Currency.PLN);
        Money money2 = new Money(new BigDecimal(19.99),Currency.PLN);

        assertNotEquals(money1,money2);
    }



    @ParameterizedTest
    @ValueSource(doubles = {20, 25, 50, 40.33, 5000.23525})
    public void trueIfHasEnoughMoney(double arg)
            throws NotMatchingCurrencyException {
        money.add(new Money(new BigDecimal(arg),Currency.PLN));

        Money moneyRequested = new Money(new BigDecimal(20),Currency.PLN);

        assertTrue(money.hasEnoughMoney(moneyRequested));

    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 5, 0, -20.33})
    public void falseIfHasNotEnoughMoney(double arg)
            throws NotMatchingCurrencyException {
        money.add(new Money(new BigDecimal(arg),Currency.PLN));

        Money moneyRequested = new Money(new BigDecimal(20),Currency.PLN);

        assertFalse(money.hasEnoughMoney(moneyRequested));

    }

    @ParameterizedTest
    @ValueSource(doubles = {20.99, 14.95, 1, Double.MAX_VALUE, Double.MIN_VALUE})
    public void trueIfAddsCorrectAmountOfMoneyToNewMoney(double paramValue)
            throws NotMatchingCurrencyException {
        Money expectedMoney = new Money(new BigDecimal(paramValue),Currency.PLN);
//        System.out.println(expectedMoney);

        money.add(new Money(new BigDecimal(paramValue),Currency.PLN));

        assertEquals(money, expectedMoney);

    }

    @Test
    public void trueIfAddsMultipleCorrect()
            throws NotMatchingCurrencyException {
        //Arrange
        BigDecimal[] bigDecArray = new BigDecimal[]
                {new BigDecimal(1.50), new BigDecimal(20.99), new BigDecimal(1000.56)};
        BigDecimal expectedValue;
        expectedValue = Arrays.stream(bigDecArray).
                reduce(new BigDecimal(0), (a, b) -> a = a.add(b));

        //Act
        for (BigDecimal value : bigDecArray) {
            money.add(new Money(value,Currency.PLN));
        }

        //Assert
        assertEquals(new Money(expectedValue,Currency.PLN), money);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.00, 2.99, 2134.1235, Double.MAX_VALUE, Double.MIN_VALUE})
    public void trueIfSubtractsCorrectValue(double paramValue)
            throws InsuffitientBalansException , NotMatchingCurrencyException {
        money.add(new Money(new BigDecimal(paramValue),Currency.PLN));
        Money expectedMoney = new Money(new BigDecimal(0),Currency.PLN);

//        System.out.println("trueIfSubtractsCorrectValue()______________________");
//        System.out.println("money: " + money + " \nexpectedMoney: " + expectedMoney);

        money.subtract(new Money(new BigDecimal(paramValue),Currency.PLN));
//        System.out.println("Money after subtraction: " + money + "\n");

        assertEquals(money,expectedMoney);
    }

    @Test
    public void trueIfSubtractsMultipleCorrect()
            throws InsuffitientBalansException, NotMatchingCurrencyException{
        //Arrange
        BigDecimal[] bigDecArray = new BigDecimal[]
                {new BigDecimal(1.50), new BigDecimal(20.99), new BigDecimal(1000.56)};
        BigDecimal sumValue = Arrays.stream(bigDecArray).
                reduce(new BigDecimal(0), (a, b) -> a = a.add(b));
        money = new Money(sumValue,Currency.PLN);
        Money expectedMoney = new Money(new BigDecimal(0),Currency.PLN);

        //Act
        for (BigDecimal value : bigDecArray) {
            money.subtract(new Money(value,Currency.PLN));
        }

        //Assert
        assertEquals(money,expectedMoney);
    }

    @Test
    public void throwsExceptionOnNotEnoughMoney(){
        //Given
        Money moneyToSubtract = new Money(new BigDecimal(10),Currency.PLN);

        //When

        //Then
        assertThrows(InsuffitientBalansException.class, () -> money.subtract(moneyToSubtract));
    }

}