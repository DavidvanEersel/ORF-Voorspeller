import Voorspeller.ORF;
import Voorspeller.Sequentie;

public class Test_main {
    public static void main(String[] args) {

        Sequentie s1 = new Sequentie("atgccctaa", "header");
        System.out.println(s1.getLengte());
        System.out.println(s1.getHeader());
        System.out.println(s1.getSequentie());
        System.out.println(s1.isCheckDNA());

        ORF o1 = new ORF("atgc", 1,1, "orf1");
        System.out.println(o1.getStopPositie());
    }


}
