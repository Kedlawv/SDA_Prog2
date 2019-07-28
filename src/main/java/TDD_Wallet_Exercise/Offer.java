package TDD_Wallet_Exercise;

import java.util.List;

public class Offer {


    private final String item;
    private List<Money> moneyList;

    public Offer(String item, List<Money> moneyList) {
        this.item = item;
        this.moneyList = moneyList;
    }

    public boolean isLowerPrice(Offer offer) throws NotMatchingCurrencyException {
        if (!this.item.equals(offer.item)) {
            return false;
        }

        for (Money myMoney : moneyList) {
            for (Money otherMoney : offer.moneyList) {
                if (myMoney.getCurrency() == otherMoney.getCurrency()
                        && myMoney.hasEnoughMoney(otherMoney)) {
                    return true;
                }
            }

        }
        return false;
    }
}
