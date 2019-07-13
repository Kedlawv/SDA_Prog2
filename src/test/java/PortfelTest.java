import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PortfelTest {

    @Test
    public void trueIfCorrectValueAdded() {
        Portfel portfel = new Portfel(new Pieniadze(new BigDecimal(0),Waluta.PLN));

        portfel.add(new Pieniadze(new BigDecimal(25), Waluta.PLN));
        BigDecimal expectedValue = new BigDecimal(25);

        assertEquals(portfel.getPln().getAmount(), expectedValue);
    }

    @Test
    public void test(){
        fail();
    }
}