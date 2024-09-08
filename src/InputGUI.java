import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.io.BufferedReader;
import java.io.FileReader;

import com.formdev.flatlaf.FlatDarculaLaf;

public class InputGUI implements ActionListener {

    DataNVars tokenObj = new DataNVars();
   
    public static JButton tokenizeButton, clearInputButton;

    public static Object getTextInputValue;
    private JTextArea inputTextArea, outputTextArea;

    private JFrame inputFrame;
    private JPanel inputPanel;
    private JScrollPane inputTextPane, outputTextPane;
    
    private String textInput;

    public InputGUI() {

        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        inputFrame = new JFrame();
        inputPanel = new JPanel(new FlowLayout());
        inputTextArea = new JTextArea();
        outputTextArea = new JTextArea();
        inputTextPane = new JScrollPane(inputTextArea);
        outputTextPane = new JScrollPane(outputTextArea);
        tokenizeButton = new JButton();
        clearInputButton = new JButton();

        inputFrame.setVisible(true);
        inputFrame.setLocationRelativeTo(null);
        inputFrame.setTitle("String Tokenizer");
        inputFrame.setSize(500,500);
        inputFrame.add(inputPanel);
       
        inputPanel.add(inputTextPane);
        inputPanel.add(tokenizeButton);     
        inputPanel.add(clearInputButton);
        inputPanel.add(outputTextPane);
        inputPanel.setVisible(true);
        inputPanel.setSize(200,20);

        inputTextPane.setPreferredSize(new Dimension(460, 100));
        inputTextPane.setBorder(null);

        outputTextPane.setPreferredSize(new Dimension(460, 300));
        inputTextPane.setBorder(null);

        inputTextArea.setFont(new Font("Courier New", Font.PLAIN, 15));
        inputTextArea.setForeground(DataNVars.standardForegroundColor);
        inputTextArea.setBackground(DataNVars.standardBackgroundColor);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setLineWrap(true);

        outputTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        outputTextArea.setBackground(DataNVars.standardBackgroundColor);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setEnabled(false);

        tokenizeButton.setVisible(true);
        tokenizeButton.setText("Tokenize Input");
        tokenizeButton.setForeground(DataNVars.standardForegroundColor);
        tokenizeButton.setFocusable(false);
        tokenizeButton.addActionListener(this);

        clearInputButton.setVisible(true);
        clearInputButton.setText("Clear Text");
        clearInputButton.setForeground(DataNVars.standardForegroundColor);
        clearInputButton.setFocusable(false);
        clearInputButton.addActionListener(this);    
    }
    public void actionPerformed(ActionEvent e) {

        // button controls
        if(e.getSource() == tokenizeButton) {

            textInput = inputTextArea.getText();
            setTextInputValue(textInput);
            
            // checks if user input is empty
            if(textInput.isEmpty()) {

                System.out.println("Input is empty. Please provide valid input.");
                outputTextArea.setText("Input is empty. Please provide valid input.");
                return;
            }
            
            // starts the tokenizing process after button press
            new InstallDelimiter(this, tokenObj);
            new TokenProcessor(tokenObj);

            // reads the token record file and display's its output to the GUI
            try (BufferedReader readFromRecord = new BufferedReader(new FileReader(TokenProcessor.recordFile)))  {

                StringBuilder recordContent = new StringBuilder();
                String lineFromRecord;
                
                while ((lineFromRecord = readFromRecord.readLine()) != null) { 
                    recordContent.append(lineFromRecord).append('\n');
                }
                
                outputTextArea.setText(recordContent.toString());
            
            } catch (Exception a) {
                
                a.printStackTrace();
                
            }
            
        } 
        // clears the input and output text area
        else if(e.getSource() == clearInputButton) {

            inputTextArea.setText("");
            outputTextArea.setText("");
        }
            
    }
    public void setTextInputValue(String textInput) {
        
        this.textInput = textInput;
    }
    public String getTextInputValue() {

        return textInput;
    }
}
