import java.util.HashMap;
import java.util.Set;

/**
 * Class Player - a player in an adventure game.
 * 
 * This class is part of the "Magical House" application. 
 * "Magical House" is a very simple, text based adventure game.
 * 
 * A "Player" represents one player in the game. A player has a 
 * bag in which it can carry items. Player can't carry all items 
 * and can only carry items up to a certain weight.
 *
 * @author Zahra Amaan(K21011879).
 * @version 2021.12.03
 */
public class Player
{
    private int maxWeight = 50;// maximum weight that the player can carry in their bag.
    HashMap<String, Integer> bag; // the players bag, stores the name of the item and its corresponding weight
    Set<String> winningItems = Set.<String>of("sushi", "pizza", "tacos", "biryani", "paella");// items that the player needs to win
    Set<String> cantTakeItems = Set.<String>of("shoe", "button", "crayon");// items that the player can't take

    /**
     * Constructor for objects of class Player
     * initialises the bag and sets the maximum weight that the player can 
     * carry in their bag
     */
    public Player()
    {
        bag = new HashMap<>();
        maxWeight = 30;
    }

    /**
     * Adds an item to the players bag.
     * @param item The item that the player wants to add to their bag.
     */
    public void addItemToBag (String item, int weight)
    {
        bag.put(item,weight);
    }
    
    /**
     * checks an item can be taken by the player. Returns true if the player 
     * can take the item and returns false if the player can't take the item.
     * @param item The item that the player wants to take.
     */
    public boolean canTake(String item)
    {
        boolean canTake = true;
        if(cantTakeItems.contains(item)){
            canTake = false;
        }
        return canTake;
    }
    
    /**
     * Removes an item from the players bag.
     * @param item The item that the player wants to remove from their bag.
     */
    public void returnItemFromBag (String item)
    {
        bag.remove(item);
    }
    
    /**
     * Calculates the total weight of all the items in the players bag.
     * @Return total The total weight of the players bag.
     */
    private int totalWeight()
    {
        int total = 0;
        for(String item : bag.keySet())
        {
           total = total + bag.get(item); 
        }
        return total;
    }
    
    /**
     * Checks if the players bag contains an item.
     * @Param item The item that you are looking for in the players bag.
     * @Return bag.containsKey(item) True if the item is in the players bag. 
     * False if the item is not in the players bag.
     */
    public boolean bagContains (String item)
    {
        return bag.containsKey(item); 
    }
    
    /**
     * Gets the weight of an item.
     * @Param item The item whose weight you want to get.
     * @Return bag.get(item) The weight of the item.
     */
    public int getItemWeight(String item)
    {
        return bag.get(item);
    }
    
    /**
     * Checks if the players bag if full. Returns true if it is and false 
     * if it is not.
     * @Return full True if the total weight of the bag is greater than or 
     * equal to the maximum weight that the player can carry. False if the
     * bag is not full.
     */
    public boolean bagFull(){
        boolean full = false;
        if (totalWeight() >= maxWeight)
        {
            full = true;
        }
        return full;
    }
    
    /**
     * Checks if the players has won the game, by checking if their bag is 
     * a winning bag. If it is then method returns true, else returns false.
     * @Return won True if the has won the game. False if the player has 
     * not won the game.
     */
    public boolean hasWon()
    {
        boolean won = false;
        if(isWinningBag()==true){
            won=true;
        }
        else{
            won=false;
        }
        return won;
    }
    
    /**
     * Generates a string of all the items in the bag.
     * @Return bagString The string of all items in the players bag.
    */
    public String getBagString(){
        String bagString = "Bag:"; 
        for(String item : bag.keySet()){
            bagString = bagString + " "+ item;
        }
        return bagString;
    }
    
    /**
     * Generates a string of all the items in the bag and the total weight 
     * of the bag.
     * @Return bagString The string of all items in the players bag and the 
     * total weight of the bag.
    */
    public String getBagStringLong(){
        String bagString = "Bag:"; 
        for(String item : bag.keySet()){
            bagString = bagString + " "+ item;
        }
        bagString = bagString + "\nTotal weight: " + totalWeight();
        return bagString;
    }
    
    /**
     * Checks if the players bag contains all the winning items. If it
     * does the method returns true, otherwise the method returns false.
     * @Return winningBag True if the players bag contains all the winning 
     * items. False if the players bag does not contain all the winning items.
     */
    private boolean isWinningBag()
    {
        boolean winningBag = false;
        Set bagItems = bag.keySet();
        if(bagItems.containsAll(winningItems)){
            winningBag = true;
        }
        return winningBag;
    }
    
    /**
     * Increases the maximum weight that the player can carry in their bag.
     */
    public void increaseMaxWeight(){
        maxWeight = maxWeight + 20;
    }
    
    /**
     * Returns the maximum weight that the player can carry.
     * @Return maxWeight The maximum weight that the player can carry.
     */
    public int getMaxWeight(){
        return maxWeight;
    }

}
