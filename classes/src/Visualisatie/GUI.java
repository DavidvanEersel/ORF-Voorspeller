package Visualisatie;
import Voorspeller.Sequentie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * GUI class
 *
 * @author Gijsbert en Margo
 */

public class GUI extends JFrame implements ActionListener {

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
        balkPaneel.setPreferredSize(new Dimension(300, 300));
        JLabel resultaatZoekterm = new JLabel();
        window.add(invulveld);
        window.add(nameField);
        window.add(balkPaneel);
        window.add(resultaatZoekterm);
        invulveld.setText("Vul hier je sequentie in. ");
    }


    /**
     * Deze methode leest het bestand met sequenties in fasta format in en maakt hier een sequentie object van.
     */
    public void readFile() {
        // Voeg > toe aan het bestand
        try {
            Files.write(Paths.get(nameField.getText()), "\n>".getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }

        // Maak headers en seqs
        ArrayList<String> headers = new ArrayList<>();
        ArrayList<String> seqs = new ArrayList<>();
        StringBuilder seq = new StringBuilder();
        try (Scanner sc = new Scanner(new File(nameField.getText()))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.startsWith(">")) {
                    headers.add(line);
                    seqs.add(seq.toString());
                    seq = new StringBuilder();
                } else {
                    seq.append(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Maak sequentie objecten
        seqs.remove(0);
        for (int i = 0; i < seqs.size(); i++){
            new Sequentie(seqs.get(i), headers.get(i));
        }
    }


    /**
     * Deze methode leest de sequentie opgegeven in het invulveld en maakt hier een sequentie object van.
     */
    public void readTekstveld() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        new Sequentie(invulveld.getText(), ">" + dtf.format(now));
    }


    /**
     * Deze methode wordt uitgevoerd als er een actie uitgevoerd is.
     *
     * @param event als er op een button wordt geklikt.
     */
    public void actionPerformed(ActionEvent event) {
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
        frame.setSize(600, 600); // zet de grootte van het frame
        frame.GUI();
        frame.setVisible(true); // geeft de GUI weer
    }
}