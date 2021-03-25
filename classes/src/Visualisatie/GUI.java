package Visualisatie;

import Database.Databasehandler;
import Voorspeller.Sequentie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * GUI class
 *
 * @author Gijsbert en Margo
 */

public class GUI extends JFrame implements ActionListener {

    private static JPanel visualisatie_orf;
    private JButton BladerKnop;
    private JTextField nameField;
    private JTextField invulveld;
    private JRadioButton kiestekst;
    private JRadioButton kiesbestand;


    /**
     * Deze methode maakt de GUI.
     */
    public void GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        BladerKnop = new JButton("open en verwerk");
        window.add(BladerKnop);
        BladerKnop.addActionListener(this);
        nameField = new JTextField(25);
        invulveld = new JTextField(25);
        JPanel balkPaneel = new JPanel();
        kiesbestand = new JRadioButton();
        kiestekst = new JRadioButton();
        ButtonGroup groep = new ButtonGroup();
        groep.add(kiestekst);
        groep.add(kiesbestand);
        window.add(kiestekst);
        window.add(kiesbestand);

        kiesbestand.setText("Om een bestand te verwerken klik hierop");
        kiestekst.setText("Om tekst te verwerken klik hierop");

        JLabel resultaatZoekterm = new JLabel();
        window.add(invulveld);
        window.add(nameField);
        window.add(resultaatZoekterm);
        invulveld.setText("Vul hier je sequentie in. ");

        visualisatie_orf = new JPanel();
        visualisatie_orf.setPreferredSize(new Dimension(500, 200));
        visualisatie_orf.setBackground(Color.white);
        window.add(visualisatie_orf);
        visualisatie_orf.setVisible(true);
    }


    /**
     * Deze methode leest het bestand met sequenties in fasta format in en maakt hier een sequentie object van.
     */
    public void readFile() {
        // Lees het bestand in
        StringBuilder inhoud = new StringBuilder();
        try (Scanner sc = new Scanner(new File(nameField.getText()))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.startsWith(">")) {
                    inhoud.append("seq_header_seperator");
                    inhoud.append(line);
                    inhoud.append("seq_header_seperator");
                } else {
                    inhoud.append(line);
                }
            }

            // Maak de sequentie objecten
            String[] lines = inhoud.toString().split("seq_header_seperator");
            ArrayList<String> lines2 = new ArrayList<>(Arrays.asList(lines));
            for (String line : lines) {
                if (line.startsWith(">")) {
                    new Sequentie(lines2.get(lines2.indexOf(line) + 1), lines2.get(lines2.indexOf(line)));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deze methode leest de sequentie opgegeven in het invulveld en maakt hier een sequentie object van.
     */
    public void readTekstveld() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        new Sequentie(invulveld.getText(), ">" + dtf.format(now));
    }


    public static void OrfVisualisatie(String seq) {
        Graphics tekenveld = visualisatie_orf.getGraphics();
        tekenveld.setColor(Color.white);
        tekenveld.fillRect(0,0,500,200);
        tekenveld.setColor(Color.black);
        tekenveld.drawString("seq", 20, 17);
        tekenveld.drawString("rf 1", 20, 27);
        tekenveld.drawString("rf 2", 20, 37);
        tekenveld.drawString("rf 3", 20, 47);
        int readframe = 20;

        int seq_lengt = seq.length();
        tekenveld.setColor(Color.blue);
        tekenveld.fillRect(50, 10, 400, 10);
        tekenveld.setColor(Color.black);
        tekenveld.drawString("0", 50, 20);
        tekenveld.drawString(String.valueOf(seq_lengt), 430, 20);

        ArrayList<String> results = Databasehandler.getResults(seq);
        System.out.println(results + "Results");

        for (String data : results) {
            String[] info_lijst = data.split("\\|");
            System.out.println(Arrays.toString(info_lijst) + "info_lijst");
            int pos = Integer.parseInt(info_lijst[2]);
            if (pos != 0) {
                pos = (pos * 400 / seq_lengt) + 50;
            } else {
                pos += 50;
            }
            int lengt = (Integer.parseInt(info_lijst[3]) - Integer.parseInt(info_lijst[2])) * 400 / seq_lengt;

            switch (info_lijst[4]) {
                case "1" -> {
                    readframe = 20;
                    tekenveld.setColor(Color.red);
                }
                case "2" -> {
                    tekenveld.setColor(Color.green);
                    readframe = 30;
                }
                case "3" -> {
                    tekenveld.setColor(Color.yellow);
                    readframe = 40;

                }
            }
            tekenveld.fillRect(pos, readframe, lengt, 10);
            tekenveld.setColor(Color.black);
            tekenveld.drawString(info_lijst[2], pos, readframe + 10);
            tekenveld.drawString(info_lijst[3], pos + lengt - 20, readframe + 10);
        }
    }


    /**
     * Deze methode wordt uitgevoerd als er een actie uitgevoerd is.
     *
     * @param event als er op een button wordt geklikt.
     */
    public void actionPerformed(ActionEvent event) {
        visualisatie_orf.removeAll();
        visualisatie_orf.revalidate();
        //visualisatie_orf.repaint();
        try {
            File selectedFile;
            int reply;
            if (event.getSource() == BladerKnop) {
                if (kiestekst.isSelected()) {
                    BladerKnop.setText("Verwerk");
                    readTekstveld();
                } else if (kiesbestand.isSelected()) {
                    JFileChooser fileChooser = new JFileChooser();
                    reply = fileChooser.showOpenDialog(this);
                    if (reply == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        nameField.setText(selectedFile.getAbsolutePath());
                        readFile();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
                | IllegalAccessException ignored) {
        }
        GUI frame = new GUI(); // maakt de frame aan
        frame.setSize(600, 400); // zet de grootte van het frame
        frame.GUI();
        frame.setVisible(true); // geeft de GUI weer

    }
}
