package Voorspeller;

public class Sequentie {

    private String sequentie;
    private String header;
    private int lengte;
    private boolean checkDNA;
    private boolean checkInDatabase;

    public Sequentie(String sequentie, String header) {

        setSequentie(sequentie);
        setHeader(header);
        setLengte();
        setCheckDNA();
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

