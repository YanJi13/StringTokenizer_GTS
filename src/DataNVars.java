import java.awt.Color;

public class DataNVars {

    public String finalTokenResults;

    // colors in the program
    public static Color standardForegroundColor = new Color(255, 220, 122);
    public static Color standardBackgroundColor = new Color(99, 99, 99);

    private String tokenizedUserInputValue;

    public void setToBeTokenValue(String tokenizedUserInputValue) {
        this.tokenizedUserInputValue = tokenizedUserInputValue;
    }
    
    public String getToBeTokenValue() {
        return this.tokenizedUserInputValue;
    }
}


