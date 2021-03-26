package Voorspeller;


/**
 * ORF class
 *
 * @author Margo en Jasper
 * @date 26-03-2021
 * @function Object ORF wordt aangemaakt met de bijbehoorende items zoals Readingframe.
 * @bugs geen bugs bekend.
 */

public class ORF {
    private String orf;
    private int readingFrame;
    private int startPositie;
    private int stopPositie;
    private int lengte;
    private String parentHeader;

    /**
     * Op het moment dat ORF wordt aangeroepen met de parameters orf, readingframe, startpos dan gaat de methode
     * door een aantal setters heen.
     *
     * @param orf          String sequentie van de orf
     * @param readingFrame int met de readingframe
     * @param startPositie int met startpos van de orf
     * @param parentHeader String met de parent header
     */
    public ORF(String orf, int readingFrame, int startPositie, String parentHeader) {
        setOrf(orf);
        setReadingFrame(readingFrame);
        setStartPositie(startPositie);
        setLengte();
        setStopPositie();
        setParentHeader(parentHeader);
    }


    /**
     * Return sequentie van de orf.
     * @return String sequentie van de orf
     */
    public String getOrf() {
        return orf;
    }


    /**
     * Zet de sequentie van de orf vast in een variabele.
     * @param orf String van de sequentie
     */
    public void setOrf(String orf) {
        this.orf = orf;
    }


    /**
     * Return readingframe.
     * @return int readingframe
     */
    public int getReadingFrame() {
        return readingFrame;
    }


    /**
     * Zet de readingframe vast in een variabele.
     * @param readingFrame int readingframe
     */
    public void setReadingFrame(int readingFrame) {
        this.readingFrame = readingFrame;
    }


    /**
     * Return de startpos.
     * @return int startpos
     */
    public int getStartPositie() {
        return startPositie;
    }


    /**
     * Zet de startpos vast in een variable.
     * @param startPositie int startpos
     */
    public void setStartPositie(int startPositie) {
        this.startPositie = startPositie;
    }


    /**
     * Return de stoppos.
     * @return int stoppos
     */
    public int getStopPositie() {
        return stopPositie;
    }


    /**
     * Zet de stoppos vast in een variable. Deze wordt berekent door de lengte op te tellen bij de startpos.
     */
    public void setStopPositie() {
        this.stopPositie = this.getStartPositie() + this.getLengte() - 1;
    }


    /**
     * Return de lengte.
     * @return int lengte
     */
    public int getLengte() {
        return lengte;
    }


    /**
     * Zet de lengte vast van de orf in een variabele.
     */
    public void setLengte() {
        this.lengte = orf.length();
    }


    /**
     * Return de parentheader.
     * @return string parentheader
     */
    public String getParentHeader() {
        return parentHeader;
    }


    /**
     * Zet de parentheader vast in een variabele.
     * @param parentHeader String parentheader
     */
    public void setParentHeader(String parentHeader) {
        this.parentHeader = parentHeader;
    }
}
