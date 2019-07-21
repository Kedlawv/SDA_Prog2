package TDD_Wallet_Exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    Wallet wallet;
    double[] testValues = new double[]{1, 5, 20.56, 2512351.346346,
            2.95, 11234};

    @BeforeEach
    public void createEmptyWallet() {
        wallet = new Wallet();
    }


    @ParameterizedTest
    @ValueSource(doubles = {1, 5, 20.56, 2512351.346346, Double.MAX_VALUE, Double.MIN_VALUE})
    public void trueIfCorrectMoneyDeposited(double paramValue) throws NotMatchingCurrencyException {
        Money depositMoney = new Money(new BigDecimal(paramValue),Currency.PLN);

        wallet.deposit(depositMoney);

        assertEquals(wallet.moneyMap.get(Currency.PLN), depositMoney);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 5, 20.56, 2512351.346346, Double.MAX_VALUE, Double.MIN_VALUE})
    public void trueIfCorrectMoneyWithdrawn(double paramValue)
            throws InsuffitientBalansException , NotMatchingCurrencyException{
        //Arrange
        Money moneyToDepositAndWithdraw = new Money(new BigDecimal(paramValue),Currency.PLN);
        Money expectedMoney = new Money(new BigDecimal(0),Currency.PLN);
        wallet.deposit(moneyToDepositAndWithdraw);

        //Act
        wallet.withdraw(moneyToDepositAndWithdraw);

        //Assert
        assertEquals(wallet.moneyMap.get(Currency.PLN), expectedMoney);


    }

    @Test
    public void trueIfMultipleDepositAndWithdrawCorrect() {
        Money expectedMoney = new Money(new BigDecimal(0),Currency.PLN);

        Arrays.stream(testValues).
                forEach(d -> {
                    try {
                        wallet.deposit(new Money(new BigDecimal(d), Currency.PLN));
                        System.out.println(wallet.getBalance());
                    } catch (NotMatchingCurrencyException e) {
                        e.printStackTrace();
                    }
                });

            Arrays.stream(testValues).forEach(d -> {
                try {
                    wallet.withdraw(new Money(new BigDecimal(d),Currency.PLN));
                    System.out.println(wallet.getBalance());
                }catch (InsuffitientBalansException e ) {

                }catch (NotMatchingCurrencyException e){

                }
            });


        assertEquals(expectedMoney,wallet.moneyMap.get(Currency.PLN));

    }

    @Test
    public void trueIfBalanceReturnsCorrect() throws NotMatchingCurrencyException{
        Money money = new Money(new BigDecimal(0),Currency.PLN);
        for (double value : testValues) {
            money.add(new Money(new BigDecimal(value),Currency.PLN));
        }

        wallet.deposit(money);


        assertEquals(wallet.getBalance().get(money.getCurrency()),money);

    }


}