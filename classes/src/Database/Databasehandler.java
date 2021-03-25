package Database;

import Voorspeller.ORF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Databasehandler {
    static String pSeq;

    public static void setResults(ArrayList<ORF> orfs, String sequentie) {
        for (ORF orf : orfs) {
            String pHeader = orf.getParentHeader().replace(" ","_") + " ";
            pSeq = sequentie + " ";
            String seqORF = orf.getOrf() + " ";
            String oStart = orf.getStartPositie() + " ";
            String oStop = orf.getStopPositie() + " ";
            int oReadingFrame = orf.getReadingFrame();
            try {
                String cmd = "bash /home/margo/Documents/HAN/'Jaar 2'/'Blok 7'/Informatica/ORF-Voorspeller/classes/src/Database/setResults.sh "
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


    public static ArrayList<String> getResults(String seq) {
        pSeq = seq;
        ArrayList<String> results = new ArrayList<>();
        try {
            String cmd = "bash /home/margo/Documents/HAN/'Jaar 2'/'Blok 7'/Informatica/ORF-Voorspeller/classes/src/Database/getResults.sh " + pSeq;
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

    public static boolean checkInDatabase(String sequentie) {
        String temp = "";
        pSeq = sequentie;
        try {
            String cmd = "bash /home/margo/Documents/HAN/'Jaar 2'/'Blok 7'/Informatica/ORF-Voorspeller/classes/src/Database/checkInDatabase.sh " + pSeq;
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
