public class Portfel {
   private Pieniadze pln;

    public Portfel(Pieniadze pln) {
        this.pln = pln;
    }

    public void add(Pieniadze pieniadze){
        this.pln = pln.add(pieniadze);
    }

    public Pieniadze getPln() {
        return pln;
    }
}
