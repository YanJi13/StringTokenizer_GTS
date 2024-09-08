/*
 * what this class basically does is accepts user input,
 * and adds the delimiter (greater than symbol)
 */
public class InstallDelimiter {

    private StringBuilder modifiedUserInput = new StringBuilder();

    private String toBeTokenizedUserInput;
    private String tokenizedUserInput;
    
    public InstallDelimiter(InputGUI inputGUI, DataNVars tokenObj) {

        // replaces ">" input with a placeholder so it wont cause
        // problems in the delimiter installation process 
        addGreaterThanSignPlaceholder(inputGUI.getTextInputValue());

        toBeTokenizedUserInput = modifiedUserInput.toString();
        tokenizedUserInput = handleSymbolsInInput(toBeTokenizedUserInput);

        // add whitespace in between words and symbols then replace it with the delimiter 
        tokenizedUserInput = tokenizedUserInput.replaceAll("\\s+", ">"); 

        tokenObj.setToBeTokenValue(tokenizedUserInput);
        
    }

    private String handleSymbolsInInput(String token) {

        StringBuilder finalToBeTokenized = new StringBuilder();
        
        // iterates through the length of token which is the user input
        for(int c = 0; c < token.length(); c++) { 
        // u get it? C++? HAHAHA (im going insane)

            char currentCharInTokens = token.charAt(c);
            
            // checks if current char in the string input to be tokenized is not a digit or a letter
            // such as signs or symbols
            if(!Character.isLetterOrDigit(currentCharInTokens)) { 
                
                // checks if the character before the current index if whitespace and if it is, 
                // adds a whitespace in between the previous character and the current one
                // used for signs usually followed by spaces like commas and periods
                if(c > 0 && token.charAt(c - 1) != ' ') { 

                    finalToBeTokenized.append(' ');
                }
                // append current symbol
                finalToBeTokenized.append(currentCharInTokens);

                // checks if the character after the current character is not a whitespace and adds one if it isnt
                // also ensures we are not at the last character
                // used for signs in between letters like apostrophes or dashes
                if(c < token.length() - 1 && token.charAt(c + 1) != ' ') {

                    finalToBeTokenized.append(' ');
                }
            } else {

            // appends characters from token (which is from user input) to finalToBeTokenized based on the for loop
            finalToBeTokenized.append(currentCharInTokens);
            }
        }

        return finalToBeTokenized.toString();
    }
    
    private StringBuilder addGreaterThanSignPlaceholder(String userInput) {
        // due to how the program puts the delimiters, it can disrupt things later in the process
        // if the user inputs a greater than sign, it can be deleted by the program thus changing the expected output

        // iterates through the user input
        for(int j = 0; j < userInput.length(); j++) {

            char inputChar = userInput.charAt(j);

            // if it finds a greater than sign, it replaces it with a placeholder
            if(inputChar == '>') {

                modifiedUserInput.append(DataNVars.putPlaceholderGTS()+ ' ');
            } else {

                modifiedUserInput.append(inputChar);
            }
        }

        return modifiedUserInput;
    }
}
