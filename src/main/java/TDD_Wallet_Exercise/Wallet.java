package TDD_Wallet_Exercise;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Wallet {

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

        Money defaultMoney = new Money(BigDecimal.ZERO,money.getCurrency());
        this.moneyMap.getOrDefault(money.getCurrency(),defaultMoney).subtract(money);

    }

    public Map getBalance() {
        return moneyMap;
    }
}
