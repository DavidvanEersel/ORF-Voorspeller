package Voorspeller;

import Database.Databasehandler;
import Visualisatie.GUI;

import java.util.ArrayList;

/**
 * Sequentie class
 *
 * @author Margo en Jasper
 */

public class Sequentie {

    private String sequentie;
    private String header;
    private int lengte;
    private boolean checkDNA;
    private boolean checkInDatabase;

    public Sequentie(String sequentie, String header) {

        setSequentie(sequentie.toUpperCase());
        setHeader(header);
        setLengte();
        setCheckDNA();
        setCheckInDatabase();


        if (this.isCheckDNA() && !this.isCheckInDatabase()) {
            Databasehandler.setResults(zoekORF(sequentie.toUpperCase(), header), this.getSequentie());
            Visualisatie.GUI.OrfVisualisatie(this.getSequentie());
        }else{
            System.out.println("zit al in de db");
        }

    }


    /**
     * Deze methode is gemaakt om ORF's te zoeken in een sequentie.
     *
     * @param seq  is de sequentie waar je ORF's van wilt vinden
     * @param head is de header van de sequentie
     * @return een ArrayList van de ORF's
     */
    private ArrayList<ORF> zoekORF(String seq, String head) {


        ArrayList<ORF> gev_orf = new ArrayList<>();

        ArrayList<Integer> start_pos = new ArrayList<>();
        ArrayList<Integer> stop_pos = new ArrayList<>();


        for (int i = 0; i < seq.length(); i++) {
            if (seq.startsWith("ATG", i)) {
                start_pos.add(i);
            }
            if (seq.startsWith("TAA", i) || seq.startsWith("TAG", i) || seq.startsWith("TGA", i)) {
                stop_pos.add(i);
            }

        }
        for (int i : start_pos) {
            for (int x : stop_pos) {
                if (i % 3 == x % 3 && i < x) {
                    String orf_Seq = seq.substring(i, x + 3);
                    int rf = i % 3 + 1;
                    gev_orf.add(new ORF(orf_Seq, rf, i, head));

                }
            }
        }
        return gev_orf;
    }

    public String getSequentie() {
        return sequentie;
    }

    public void setSequentie(String sequentie) {
        this.sequentie = sequentie;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte() {
        this.lengte = sequentie.length();
    }

    public boolean isCheckDNA() {

        return checkDNA;
    }

    public void setCheckDNA() {
        this.checkDNA = sequentie.toUpperCase().matches("^[ACGT]*$");
    }

    public boolean isCheckInDatabase() {
        return checkInDatabase;
    }

    public void setCheckInDatabase() {
        this.checkInDatabase = Databasehandler.checkInDatabase(getSequentie());
    }
}
