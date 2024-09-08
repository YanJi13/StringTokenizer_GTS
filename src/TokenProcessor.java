import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

/*
 * what this class does is process the user input based on the delimiter
 * tokenizes the user input and classifies its elements
 * also breaks up the elements into their individual characters
 * then counts how many are each type of token
 * and lastly, puts the result in a record
 */

public class TokenProcessor {

    private ArrayList<String> tokensToProcess = new ArrayList<>();

    public static File recordFile;

    private String filename;
    private int fileNameCounter;
    private int numberCount;
    private int mathsymCount;
    private int letterCapCount;
    private int letterLowCount;
    private int wordCapCount;
    private int wordLowCount;
    private int alphanumericsCount;
    private int punctuationCount;
    private int unknownCharCount;

    public TokenProcessor(DataNVars tokenObj) {

        fileNameCounter = 1;
        filename = null;
        recordFile = null;

        // creates a file with a unique file name
        while (true) {
            filename = "TokenRecord" + fileNameCounter + ".txt";
            recordFile = new File(filename);
            
            // checks if a file name already exists
            if (!recordFile.exists()) {
                break;
            }

            // adds to the file name counter which then makes the next file name unique
            fileNameCounter++;
        }

        try {
            BufferedWriter writeToRecord = new BufferedWriter(new FileWriter(recordFile));

            Scanner seekDelimiters = new Scanner(tokenObj.getToBeTokenValue());

            // removes delimiters
            seekDelimiters.useDelimiter("[>]");

            System.out.println(tokenObj.getToBeTokenValue());

            // adds the elements of the user input to an array list
            while (seekDelimiters.hasNext()) {
                String tokenNoDelim = seekDelimiters.next();
                tokensToProcess.add(tokenNoDelim);
            }
            seekDelimiters.close();
            
            // phase 1 header for clarity
            writeToRecord.write(String.format("\n%-30s %-20s", "Token", "Classification"));
            writeToRecord.write("\n" + "-".repeat(50) + "\n");

            for (int k = 0; k < tokensToProcess.size(); k++) {

                writeToRecord.write(String.format("%-30s -> %-20s", "\"" + tokensToProcess.get(k) + "\"", classifyTokens(tokensToProcess.get(k))));
                writeToRecord.newLine();
            }

            // phase 2 header for clarity
            writeToRecord.write(String.format("\n%-30s %-20s", "Token", "Individual Characters"));
            writeToRecord.write("\n" + "-".repeat(50) + "\n");

            for (int a = 0; a < tokensToProcess.size(); a++) {
                writeToRecord.write(String.format("%-30s -> ", "\"" + tokensToProcess.get(a) + "\""));

                // Get the current token as a String
                String charInToken = tokensToProcess.get(a);

                // get each character in the token
                for (int b = 0; b < charInToken.length(); b++) {
                    
                    // Print each character with a fixed width
                    writeToRecord.write(String.format("%-3s", "'" + charInToken.charAt(b) + "'"));
                }

                // Move to the next line after printing all characters of the token
                writeToRecord.newLine();               
            }

            // phase 3 header for clarity
            writeToRecord.write(String.format("\n%-30s %-20s", "Token Type", "Toke Type Count: "));
            writeToRecord.write("\n" + "-".repeat(50) + "\n");

            // writes the types of tokens in the user input and then their count
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Number" + "\"", numberCount));
            writeToRecord.newLine();
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Math Symbol" + "\"", mathsymCount));
            writeToRecord.newLine();
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Letter - Capital" + "\"", letterCapCount));
            writeToRecord.newLine();
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Letter - Lowercase" + "\"", letterLowCount));
            writeToRecord.newLine();
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Word - Capitalized" + "\"", wordCapCount));
            writeToRecord.newLine();
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Word - Lowercase" + "\"", wordLowCount));
            writeToRecord.newLine();
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Alphanumerics" + "\"", alphanumericsCount));
            writeToRecord.newLine();
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Punctuation" + "\"", punctuationCount));
            writeToRecord.newLine();
            writeToRecord.write(String.format("%-30s -> %-20s", "\"" + "Out of Range" + "\"", unknownCharCount));
            
            writeToRecord.close();

        } catch (Exception e) {
            System.out.println("an error has occurred in file recording...");
        }
    }
    
    public String classifyTokens(String token) { // this method compares the tokens to regular expressions

        if (token.matches("\\d+")) {

            numberCount++;
            return "Number";
        
        }  else if (token.matches("[+\\-*/=<]")) {
            
            mathsymCount++;
            return "Math Symbol";
        
        } else if (token.matches("[A-Z]")) {

            letterCapCount++;
            return "Letter - Capital";
        
        } else if (token.matches("[a-z]")) {

            letterLowCount++;
            return "Letter - Lowercase";
        
        } else if (token.matches("[A-Z][a-z]*")) {

            wordCapCount++;
            return "Word - Capitalized";
        
        } else if (token.matches("[a-z]+")) {

            wordLowCount++;
            return "Word - Lowercase";
        
        } else if (token.matches("[a-zA-Z0-9]+")) {

            alphanumericsCount++;
            return "Alphanumerics";
        
        } else if (token.matches("\\p{Punct}")) {

            punctuationCount++;
            return "Punctuation";
            
        } else {

            unknownCharCount++;
            return "Out of Range";
        }
    }
}


