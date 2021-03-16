package Voorspeller;

import java.util.ArrayList;

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
        System.out.println(zoekORF(sequentie.toUpperCase(), header));

    }

    private ArrayList<ORF> zoekORF(String seq, String head) {

        ArrayList<ORF> gev_orf = new ArrayList<>();

        ArrayList<Integer> start_pos = new ArrayList<>();
        ArrayList<Integer> stop_pos = new ArrayList<>();



        for(int i = 0; i< seq.length(); i++) {
            if (seq.startsWith("ATG", i)){
                start_pos.add(i);
            }
            if (seq.startsWith("TAA", i) || seq.startsWith("TAG", i) || seq.startsWith("TGA", i)) {
                stop_pos.add(i);
            }

        }
        for (int i: start_pos) {
            for (int x : stop_pos) {
                if (i%3 == x%3 && i<x) {
                    String orf_Seq = seq.substring(i, x+3);
                    int rf = i%3+1;
                    gev_orf.add(new ORF(orf_Seq,rf,i,head));

                }
            }
        } return gev_orf;


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

    public void setCheckInDatabase(boolean checkInDatabase) {
        //query voor check in database nog inbouwen
        this.checkInDatabase = checkInDatabase;
    }

}

