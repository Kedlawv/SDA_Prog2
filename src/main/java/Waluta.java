import java.math.BigDecimal;

public enum Waluta {
    EURO(new BigDecimal(0.23)),GBP(new BigDecimal(0.21)),PLN(new BigDecimal(1))
    ,USD(new BigDecimal (0.26));

    private BigDecimal przelicznik;

    Waluta(BigDecimal przelicznik) {
        this.przelicznik = przelicznik;
    }

    public BigDecimal getPrzelicznik() {
        return przelicznik;
    }
}
