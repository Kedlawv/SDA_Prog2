public enum Currency {

    EURO(230L),GBP(210L),PLN(100L),USD(260L);

    private long przelicznik;

    Currency(long exchangeRate) {
        this.przelicznik = exchangeRate;
    }

    public long getExchangeRate() {
        return przelicznik;
    }
}
