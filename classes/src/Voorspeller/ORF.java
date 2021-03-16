package Voorspeller;

public class ORF {
    private String orf;
    private int readingFrame;
    private int startPositie;
    private int stopPositie;
    private int lengte;
    private String parentHeader;

    public ORF (String orf, int readingFrame, int startPositie, String parentHeader){
        setOrf(orf);
        setReadingFrame(readingFrame);
        setStartPositie(startPositie);
        setLengte();
        setStopPositie();
        setParentHeader(parentHeader);
    }

    public String getOrf() {
        return orf;
    }

    public void setOrf(String orf) {
        this.orf = orf;
    }

    public int getReadingFrame() {
        return readingFrame;
    }

    public void setReadingFrame(int readingFrame) {
        this.readingFrame = readingFrame;
    }

    public int getStartPositie() {
        return startPositie;
    }

    public void setStartPositie(int startPositie) {
        this.startPositie = startPositie;
    }

    public int getStopPositie() {
        return stopPositie;
    }

    public void setStopPositie() {
        this.stopPositie = startPositie + lengte -1;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte() {
        this.lengte = orf.length();
    }

    public String getParentHeader() {
        return parentHeader;
    }

    public void setParentHeader(String parentHeader) {
        this.parentHeader = parentHeader;
    }



}
