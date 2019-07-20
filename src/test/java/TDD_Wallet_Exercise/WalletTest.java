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
    double[] testValues = new double[]{1, 5, 20.56, 2512351.346346, Double.MAX_VALUE, Double.MIN_VALUE,
            2.95, 11234};

    @BeforeEach
    public void createEmptyWallet() {
        wallet = new Wallet();
    }

    @Test
    public void trueIfEmptyWalletCreationCorrect() {
        Currency expectedCurrency = Currency.PLN;
        BigDecimal expectedAmount = new BigDecimal(0);

        assertEquals(expectedCurrency, wallet.getMoney().getCurrency());
        assertEquals(expectedAmount, wallet.getMoney().getAmount());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 5, 20.56, 2512351.346346, Double.MAX_VALUE, Double.MIN_VALUE})
    public void trueIfCorrectMoneyDeposited(double paramValue) {
        Money depositMoney = new Money(new BigDecimal(paramValue));

        wallet.deposit(depositMoney);

        assertEquals(wallet.money, depositMoney);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 5, 20.56, 2512351.346346, Double.MAX_VALUE, Double.MIN_VALUE})
    public void trueIfCorrectMoneyWithdrawn(double paramValue) throws InsuffitientBalansException {
        //Arrange
        Money moneyToDepositAndWithdraw = new Money(new BigDecimal(paramValue));
        Money expectedMoney = new Money(new BigDecimal(0));
        wallet.deposit(moneyToDepositAndWithdraw);

        //Act
        wallet.withdraw(moneyToDepositAndWithdraw);

        //Assert
        assertEquals(wallet.money, expectedMoney);


    }

    @Test
    public void trueIfMultipleDepositAndWithdrawCorrect() {
        Money expectedMoney = new Money(new BigDecimal(0));

        Arrays.stream(testValues).forEach(d -> wallet.deposit(new Money(new BigDecimal(d))));

            Arrays.stream(testValues).forEach(d -> {
                try {
                    wallet.withdraw(new Money(new BigDecimal(d)));
                }catch (InsuffitientBalansException e) {

                }
            });


        assertEquals(wallet.money, expectedMoney);

    }

    @Test
    public void trueIfBalanceReturnsCorrect() {
        Money money = new Money(new BigDecimal(0));
        for (double value : testValues) {
            money.add(new Money(new BigDecimal(value)));
        }

        wallet.deposit(money);


        assertEquals(wallet.getBalance(),money);

    }


}