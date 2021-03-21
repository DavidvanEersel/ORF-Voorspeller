package Visualisatie;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {

    private JButton BladerKnop;
    private JTextField nameField;
    private JTextField invulveld;
    private JRadioButton kiestekst;
    private JRadioButton kiesbestand;


    public GUI() {
    }

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
        invulveld.setText("Vul hier je sequenties in. ");
    }


    public void readFile() throws IOException {
        System.out.println("bestand wordt gelezen");
        //BufferedReader inFile = new BufferedReader(new FileReader(nameField.getText()));
        //String line; // variabele waarin de gelezen tekst wordt opgenomen
        //String invulling = invulveld.getText(); // haalt de gezochte term op uit de textfield
        //int[] output = new int[4]; // lijst waarin later de resultaten worden opgeslagen
        //while ((line = inFile.readLine()) != null) { // een loop waarin het bestand wordt gelezen
        //   output = Calc.zoeker(line); // deze geeft de zoekterm mee en de inhoud van het bestand daarnaast ontvangt die de resultaen en slaat die die op
        //}
    }


    public void readTekstveld() {
        System.out.println("tekst wordt gelezen.");
        String seq = invulveld.getText();
        String[] output = new String[4];
        // output=Calc.orfzoeker(seq);

    }

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
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ignored) {
        }
        GUI frame = new GUI(); // maakt de frame aan
        frame.setSize(600, 600); // zet de groote van het frame
        frame.GUI();
        frame.setVisible(true); // geeft de GUI weer
    }
}
