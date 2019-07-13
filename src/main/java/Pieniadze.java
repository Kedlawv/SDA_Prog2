import java.math.BigDecimal;

public class Pieniadze {
    BigDecimal amount;
    Waluta waluta;

    public Pieniadze(BigDecimal ammount, Waluta waluta) {
        this.amount = ammount;
        this.waluta = waluta;
    }

    public BigDecimal getAmount() {
        return amount;
    }



    public Waluta getWaluta() {
        return waluta;
    }


    public Pieniadze add(Pieniadze p) {
        if (!(this.getWaluta() == p.getWaluta())) {
            return null;

        }
        amount = this.getAmount().add(p.getAmount());
        return this;

    }



}
