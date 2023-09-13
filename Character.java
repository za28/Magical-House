import java.util.HashMap;

/**
 * Class Character - a Character in an adventure game.
 * 
 * This class is part of the "Magical House" application. 
 * "Magical House" is a very simple, text based adventure game.
 * 
 * A "Character" represents one character in the game. 
 * A character has speeches. Each speech is for a particular room.
 *
 * @author Zahra Amaan(K21011879).
 * @version 2021.12.03
 */
public class Character
{
    private Room currentRoom;
    private HashMap<Room, String> speeches;

    /**
     * Constructor for objects of class Character
     * Initialises the current room of the character.
     * @Param room The room you want to initialise the characters current room to.
     */
    public Character(Room room)
    {
        currentRoom = room;
        speeches = new HashMap<>();
    }
    
     /**
     * Adds the characters speech for a room.
     * @param  room The room you want to add the characters speech for.
     */
    public void addSpeech(Room room, String speech)
    {
        speeches.put(room,speech);//key is the room and the value is the speech
    }
    
     /**
     * Generates a string of the characters speech for a room.
     * @param  room The room you want to get the characters speech for.
     * @Return speech The characters speech for a room.
     */
    public String getSpeech(Room room){
        String speech = "";
        speech = speech + speeches.get(room);//returns the value associated with the key
        return speech;
    }
    
     /**
     * Checks if a character is in a room.
     * @param  room The room you want to check the character is is.
     * @Return (room==currentRoom) True if a character is in the 
     * room provided. False if a character is not in the room
     */
    public boolean inRoom(Room room){
        return (room==currentRoom);
    }
    
    /**
     * Changes the current room of the character.
     * @param  room The room to change the characters room to.
     */
    public void changeRoom(Room room){
        currentRoom = room;
    }
}
