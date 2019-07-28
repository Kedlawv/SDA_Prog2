package TDD_Wallet_Exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Wallet {

    Logger LOGGER = LoggerFactory.getLogger(Wallet.class);
    Map<Currency,Money> moneyMap;

    {
        moneyMap = new HashMap<Currency,Money>();
    }


    public void deposit(Money money) throws NotMatchingCurrencyException {

        if(moneyMap.containsKey(money.getCurrency())){
            Money currentMoney = moneyMap.get(money.getCurrency());
            currentMoney.add(money);
        }else{
            moneyMap.put(money.getCurrency(),money);
        }
    }

    public void withdraw(Money money)
            throws InsuffitientBalansException, NotMatchingCurrencyException {

        if(moneyMap.containsKey(money.getCurrency())) {
            this.moneyMap.get(money.getCurrency()).subtract(money);
        }else{
            LOGGER.error("Withdraw() throw");
            throw new InsuffitientBalansException();
        }
    }

    public Map getBalance() {
        return moneyMap;
    }
}
