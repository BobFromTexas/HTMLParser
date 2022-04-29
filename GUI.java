import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Graphic User Interface for user to enter URL
 */
public class GUI implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel instructionLabel;
    private static JLabel message;
    private static JTextField urlEntry;
    private static JButton button;


    public GUI(){
    }

    /**
     * Takes user entered URL
     * Uses a button to run business logic
     */
    public void runGUI(){
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(650, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        panel.setLayout(null);

        instructionLabel = new JLabel("Enter URL here:");
        instructionLabel.setBounds(10, 20, 150, 25);
        panel.add(instructionLabel);

        urlEntry = new JTextField(20);
        urlEntry.setBounds(110,20,400,25);
        panel.add(urlEntry);

        button = new JButton("Process URL");
        button.setBounds(175,60,150,25);
        button.addActionListener(new GUI());
        panel.add(button);

        message = new JLabel("");
        message.setBounds(10, 110, 500,25);
        panel.add(message);

        frame.setVisible(true);
    }

    /**
     * Processes target URL
     * Generates the operable links from target URL
     * Accesses and processes text passed from generated links
     * Writes the text to consolidated plain text file
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String url = urlEntry.getText();
        TextProcessor textProcessor = new TextProcessor(url);
        textProcessor.createLinks();
        //textProcessor.displayLinks(); // print to console to test
        textProcessor.processLinks();
        System.out.println(url + " has been processed"); // to console to test
        message.setText("URL " + url + " has been processed");
    }
}
