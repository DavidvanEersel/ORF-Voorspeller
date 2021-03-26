package Voorspeller;

import Database.Databasehandler;

import java.util.ArrayList;

/**
 * Sequentie class
 *
 * @author Margo en Jasper
 * @date 26-03-2021
 * @function Object sequentie wordt aangemaakt met de bij behoorende items zoals sequentie.
 * @bugs De sequentie mag niet te groot zijn, anders treedt er een Mememory error op.
 */

public class Sequentie {

    private String sequentie;
    private String header;
    private int lengte;
    private boolean checkDNA;
    private boolean checkInDatabase;

    /**
     * Op het moment dat Sequentie wordt aangeroepen met de parameters sequentie en header dan gaat de methode
     * door een aantal setters heen. Hierbij zijn er een aantal restricties toegevoegd.
     * @param sequentie string met sequentie
     * @param header string met de header
     */
    public Sequentie(String sequentie, String header) {
        setSequentie(sequentie);
        setHeader(header);
        setLengte();
        setCheckDNA();
        setCheckInDatabase();

        if (this.isCheckDNA() && !this.isCheckInDatabase()) {
            Databasehandler.setResults(zoekORF(this.getSequentie(), this.getHeader()), this.getSequentie());

            Visualisatie.GUI.OrfVisualisatie(this.getSequentie());
        } else if (this.isCheckInDatabase()) {
            System.out.println("De sequentie zit al in de database.");
        } else {
            System.out.println("De sequentie is geen DNA-sequentie.");
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

        // Zoek de start- en stopposities van de ORF's.
        for (int i = 0; i < seq.length(); i++) {
            if (seq.startsWith("ATG", i)) {
                start_pos.add(i);
            }
            if (seq.startsWith("TAA", i) || seq.startsWith("TAG", i) || seq.startsWith("TGA", i)) {
                stop_pos.add(i);
            }
        }

        // Haal de sequentie van het ORF op.
        boolean door = true;
        for (int i : start_pos) {
            door = true;
            for (int x : stop_pos) {
                if (door) {
                    if (i % 3 == x % 3 && i < x) {
                        String orf_Seq = seq.substring(i, x + 3);
                        int rf = i % 3 + 1;
                        gev_orf.add(new ORF(orf_Seq, rf, i, head));
                        door = false;
                    }
                }
            }
        }
        return gev_orf;
    }


    /**
     * Return sequentie.
     * @return string sequentie
     */
    public String getSequentie() {
        return sequentie;
    }


    /**
     * Zet de sequentie vast in een variabele.
     * @param sequentie string met sequentie
     */
    public void setSequentie(String sequentie) {
        this.sequentie = sequentie.toUpperCase();
    }


    /**
     * Return de header.
     * @return string header
     */
    public String getHeader() {
        return header;
    }


    /**
     * Zet de header vast in een variabele.
     * @param header string met header
     */
    public void setHeader(String header) {
        this.header = header;
    }


    /**
     * Return de lengte van de sequentie.
     * @return int lengte
     */
    public int getLengte() {
        return lengte;
    }


    /**
     * Zet de lengte van de sequentie vast in een variabele.
     */
    public void setLengte() {
        this.lengte = sequentie.length();
    }


    /**
     * Return een boolean of het DNA is.
     * @return boolean, true of false
     */
    public boolean isCheckDNA() {
        return checkDNA;
    }


    /**
     * Zet de boolean vast of het een DNA sequentie is. Dit wordt gedaan met een regex.
     */
    public void setCheckDNA() {
        this.checkDNA = sequentie.toUpperCase().matches("^[ACGT]*$");
    }


    /**
     * Return de boolean of de sequentie in de database staat.
     * @return boolean, true of false
     */
    public boolean isCheckInDatabase() {
        return checkInDatabase;
    }


    /**
     * Zet de boolean vast in een variable. Dit wordt gedaan door een methode aan te roepen
     * in een andere class.
     */
    public void setCheckInDatabase() {
        this.checkInDatabase = Databasehandler.checkInDatabase(getSequentie());
    }
}
