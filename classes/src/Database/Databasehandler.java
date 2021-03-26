package Database;

import Voorspeller.ORF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Databasehandler class
 *
 * @author David van Eersel
 * @date 26-03-2021
 * @function zorgt ervoor dat er een communicatie is met de database.
 * @bugs bestanden hebben niet altijd de rechten of iets te mogen uit te voeren. Op dat moment treedt er een exceptie op
 */

public class Databasehandler {
    static String pSeq;

    /**
     * Deze methode zet de resultaten in de Database
     *
     * @param orfs      een arraylist met alle orf objecten
     * @param sequentie een string met daarin de sequentie
     */
    public static void setResults(ArrayList<ORF> orfs, String sequentie) {
        for (ORF orf : orfs) {
            String pHeader = orf.getParentHeader().replace(" ", "_") + " ";
            pSeq = sequentie + " ";
            String seqORF = orf.getOrf() + " ";
            String oStart = orf.getStartPositie() + " ";
            String oStop = orf.getStopPositie() + " ";
            int oReadingFrame = orf.getReadingFrame();
            try {
                // Voer hier het path in naar het bestand setResults.sh.
                String cmd = "/home/daaf/IdeaProjects/ORF-Voorspeller/classes/src/Database/setResults.sh "
                        + pHeader + pSeq + seqORF + oStart + oStop + oReadingFrame;
                Process process = Runtime.getRuntime().exec(cmd);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                process.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Deze methode haalt de resultaten uit de database
     *
     * @param seq de string met de sequentie die gebeurt wordt om te zoeken naar de sequntie
     * @return een aaraylist met strings wat de resultaten zijn
     */
    public static ArrayList<String> getResults(String seq) {
        pSeq = seq;
        ArrayList<String> results = new ArrayList<>();
        try {
            // Voer hier het path in naar het bestand getResults.sh.
            String cmd = "/home/daaf/IdeaProjects/ORF-Voorspeller/classes/src/Database/getResults.sh " + pSeq;
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                results.add(line);
            }
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }


    /**
     * Deze methode check of de sequentie al in de database zit
     *
     * @param sequentie de string met de sequentie die kijkt of de sequentie in de database staat
     * @return boolean of hij in de database staat
     */
    public static boolean checkInDatabase(String sequentie) {
        String temp = "";
        pSeq = sequentie;
        try {
            // Voer hier het path in naar het bestand checkInDatabase.sh
            String cmd = "/home/daaf/IdeaProjects/ORF-Voorspeller/classes/src/Database/checkInDatabase.sh " + pSeq;
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    temp = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (!temp.equals(""));
    }
}
