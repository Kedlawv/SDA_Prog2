package TDD_Wallet_Exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferTest {

    List<Money> moneyListBuyer;
    List<Money> moneyListSeller;
    Money pln10;
    Money pln20;
    Money euro19_99;
    Money euro10_99;

    @BeforeEach
    public void createMoneyListandMoney(){
        moneyListBuyer = new ArrayList<>();
        moneyListSeller = new ArrayList<>();
        pln10 = new Money(new BigDecimal(10),Currency.PLN);
        pln20 = new Money(new BigDecimal(20),Currency.PLN);
        euro10_99 = new Money(new BigDecimal(10.99),Currency.EURO);
        euro19_99 = new Money(new BigDecimal(19.99),Currency.EURO);


    }

    @Test
    public void trueIfIsLowerPriceOneCurrency() throws NotMatchingCurrencyException {
        moneyListBuyer.add(pln10);
        Offer buyingOffer = new Offer("Football", moneyListBuyer);
        Offer sellingOffer = new Offer("Football", moneyListBuyer);

        boolean result = buyingOffer.isLowerPrice(sellingOffer);
        assertTrue(result);
    }

    @Test
    public void falseIfIsHigherPriceOneCurrency() throws NotMatchingCurrencyException {
        moneyListBuyer.add(pln10);
        moneyListSeller.add(pln20);
        Offer buyingOffer = new Offer("Football", moneyListBuyer);
        Offer sellingOffer = new Offer("Football", moneyListSeller);

        boolean result = buyingOffer.isLowerPrice(sellingOffer);
        assertFalse(result);
    }

    @Test
    public void falseIfIsHigherPriceMultipleCurrency() throws NotMatchingCurrencyException {
        moneyListBuyer.add(pln10);
        moneyListBuyer.add(euro10_99);
        moneyListSeller.add(pln20);
        moneyListSeller.add(euro19_99);
        Offer buyingOffer = new Offer("Football", moneyListBuyer);
        Offer sellingOffer = new Offer("Football", moneyListSeller);

        boolean result = buyingOffer.isLowerPrice(sellingOffer);
        assertFalse(result);
    }

    @Test
    public void trueIfIsLowerPriceMultipleCurrency() throws NotMatchingCurrencyException {
        moneyListBuyer.add(pln20);
        moneyListBuyer.add(euro19_99);
        moneyListSeller.add(pln10);
        moneyListSeller.add(euro10_99);
        Offer buyingOffer = new Offer("Football", moneyListBuyer);
        Offer sellingOffer = new Offer("Football", moneyListSeller);

        boolean result = buyingOffer.isLowerPrice(sellingOffer);
        assertTrue(result);
    }



}