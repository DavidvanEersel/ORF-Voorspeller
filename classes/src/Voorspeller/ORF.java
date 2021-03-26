package Voorspeller;


/**
 * ORF class
 *
 * @author Margo en Jasper
 */

public class ORF {
    private String orf;
    private int readingFrame;
    private int startPositie;
    private int stopPositie;
    private int lengte;
    private String parentHeader;

    /**
     * op het moment dat ORF wordt aangeroepen met de parameters orf, readingframe, startpos dan gaat de methode
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
     * return sequentie van de orf
     * @return String sequentie van de orf
     */
    public String getOrf() {
        return orf;
    }

    /**
     * zet de sequentie van de orf vast in een variable
     * @param orf String van de sequentie
     */
    public void setOrf(String orf) {
        this.orf = orf;
    }

    /**
     * return readingframe
     * @return int readingframe
     */
    public int getReadingFrame() {
        return readingFrame;
    }

    /**
     * zet de readingframe vast in een variable
     * @param readingFrame int readingframe
     */
    public void setReadingFrame(int readingFrame) {
        this.readingFrame = readingFrame;
    }

    /**
     * return de startpos
     * @return int startpos
     */
    public int getStartPositie() {
        return startPositie;
    }

    /**
     * zet de startpos vast in een variable
     * @param startPositie int startpos
     */
    public void setStartPositie(int startPositie) {
        this.startPositie = startPositie;
    }

    /**
     * return de stoppos
     * @return int stoppos
     */
    public int getStopPositie() {
        return stopPositie;
    }

    /**
     * zet de stoppos vast in een variable. Deze wordt berekent door de lengte op te tellen bij de startpos
     */
    public void setStopPositie() {
        this.stopPositie = this.getStartPositie() + this.getLengte() - 1;
    }

    /**
     * return de lengte
     * @return int lengte
     */
    public int getLengte() {
        return lengte;
    }

    /**
     * zet de lengte vast van de orf in een variable
     */
    public void setLengte() {
        this.lengte = orf.length();
    }

    /**
     * return de parentheader
     * @return string parentheader
     */
    public String getParentHeader() {
        return parentHeader;
    }

    /**
     * zet de parentheader vast in een variable
     * @param parentHeader String parentheader
     */
    public void setParentHeader(String parentHeader) {
        this.parentHeader = parentHeader;
    }
}
