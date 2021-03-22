package Database;

import Voorspeller.ORF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Databasehandler {

    public static void setResults(ArrayList<ORF> orfs, String sequentie) {
        for (ORF orf : orfs) {
            String pHeader = orf.getParentHeader() + " ";
            String pSeq = sequentie + " ";
            String seqORF = orf.getOrf() + " ";
            String oStart = orf.getStartPositie() + " ";
            String oStop = orf.getStopPositie() + " ";
            int oReadingFrame = orf.getReadingFrame();

            try {
                String cmd = "bash /home/daaf/IdeaProjects/ORF-Voorspeller/classes/src/Database/setResults.sh "
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


    //public ArrayList<String> getResults() {

    //}
}
