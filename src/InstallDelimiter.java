/*
 * what this class basically does is accepts user input,
 * and adds the delimiter (greater than symbol)
 */
public class InstallDelimiter {

    private String toBeTokenizedUserInput;
    private String tokenizedUserInput;
    
    
    public InstallDelimiter(InputGUI inputGUI, DataNVars tokenObj) {
        
        toBeTokenizedUserInput = inputGUI.getTextInputValue();
        tokenizedUserInput = handleSymbolsInInput(toBeTokenizedUserInput);

        // add delimiters automatically based on token classificaton
        if(InputGUI.autoDeTo == true) {

            // replaces all whitespace with the delimiter
            tokenizedUserInput = tokenizedUserInput.replaceAll("\\s+", ">");
            tokenObj.setToBeTokenValue(tokenizedUserInput);
            InputGUI.autoDeTo = false;
        
        // user needs to put the delimiters manually
        } else {

            // add whitespace in between words and symbols then replace it with the delimiter 
            tokenizedUserInput = tokenizedUserInput.replaceAll("\\s+", "");
            tokenObj.setToBeTokenValue(tokenizedUserInput);
        }       
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
                
                // checks if the character before the current index is not whitespace and if it isnt, 
                // adds a whitespace in between the previous character and the current one
                // used for signs usually followed by spaces like commas and periods
                if(c > 0 && token.charAt(c - 1) == ',' || token.charAt(c - 1) == '.') { 

                    finalToBeTokenized.append(' ');
                }
                // append current symbol
                finalToBeTokenized.append(currentCharInTokens);

            } else {

            // appends characters from token (which is from user input) to finalToBeTokenized based on the for loop
            finalToBeTokenized.append(currentCharInTokens);
            }
        }

        return finalToBeTokenized.toString();
    }
}
