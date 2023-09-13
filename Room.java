import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Magical House" application. 
 * "Magical House" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighbouring room. A room contains items. For 
 * each item, the room stores the name of the item and the weight 
 * of the item.
 * 
 * @author  Michael Kölling, David J. Barnes and Zahra Amaan(K21011879).
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    private HashMap<String, Integer> items; // stores the items in this room.
    private String directions; // the directions to all other rooms from this room.
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Adds an item to this room.
     * @param item The name of the item to add.
     * @param weight The weight of the item.
     */
    public void addItem(String item, int weight) 
    {
        items.put(item, weight);
    }
    
    /**
     * Removes an item from this room.
     * @param item The name of the item to remove.
     */
    public void removeItem(String item) 
    {
        items.remove(item);
    }
    
    /**
     * Checks if the room contains an item.
     * @Param item The item that you are looking for in the room.
     * @Return items.containsKey(item) True if the item is in the room. 
     * False if the item is not in the room.
     */
    public boolean containsItem(String item)
    {
        return items.containsKey(item);
    }
    
    /**
     * Gets the weight of an item.
     * @Param item The item whose weight you want to get.
     * @Return items.get(item) The weight of the item.
     */
    public int getItemWeight(String item)
    {
        return items.get(item);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getItemsString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Return a string describing the room's items, for example
     * "Items: pizza pretzel shoe".
     * @return Details of the room's items.
     */
    private String getItemsString()
    {
        String itemsString = "Items:";
        for(String item : items.keySet())
        {
            itemsString = itemsString + " " + item;
        }
        return itemsString;
    }
    
    /**
     * Define the directions from the room to all other rooms.
     * @param directions The directions from the room to all other rooms.
     */
    public void setDirections(String directions){
        this.directions = directions;
    }
    
    /**
     * Return a string describing the directions to all other rooms from 
     * the room.
     * @return Details of the directions to get to all other rooms from 
     * this room.
     */
    public String getDirections(){
        return directions;
    }
}

