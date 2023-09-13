/**
 * This class is part of the "Magical House" application. 
 * "Magical House" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling, David J. Barnes and Zahra Amaan(K21011879).
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "back", "take", "return", "ask", "directions", "map", "bag", "gain"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * @Return validCommands The string of all valid command words.
     */
    public String getAll() 
    {
        String validCommandWords = "";
        for(String command: validCommands) {
            validCommandWords = validCommandWords + " " + command;
        }
        return validCommandWords;
    }
}
