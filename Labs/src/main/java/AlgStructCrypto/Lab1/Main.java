package AlgStructCrypto.Lab1;

public class Main {
    public static void main(String args[]){
        LongNum a = new LongNum();
        LongNum b = new LongNum();
        a.fromString("99999999");
        b.fromString("913499");
        LongNum c = a.multiply(b);
        LongNum d = a.addition(b);
        LongNum e = a.subst(b);
        LongNum k = a.divide(b);
        System.out.println(k.getString());
        //System.out.println(a.sqrt().getString());
        System.out.println(c.getString());
        System.out.println(d.getString());
        System.out.println(e.getString());
        System.out.println((b.compareWith(a)));
   }
}
