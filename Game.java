import java.util.Random;
import java.util.Stack;

/**
 *  This class is the main class of the "Magical House" application. 
 *  "Magical House" is a very simple, text based adventure game.  Users 
 *  can walk around a house.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser, crates the player, creates the characters
 *  and starts the game.  It also evaluates and executes the commands 
 *  that the parser returns.
 * 
 * @author  Michael Kölling, David J. Barnes and Zahra Amaan(K21011879).
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> previousRoom = new Stack<>();
    private Room portalRoom;
    private Player player;
    private Room[] rooms;
    private Character dragon;
    private Character fairy;
        
    /**
     * Create the game, initialise its internal map and array of rooms.
     */
    public Game() 
    {
        rooms = new Room[5];
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms, link their exits together and add items and characters to the rooms.
     */
    private void createRooms()
    {
        Room japan, italy, mexico, india, spain;
        
        // create the rooms
        
        portalRoom = new Room("in the portal.");
        
        japan = new Room("in Japan");
        italy = new Room("in Italy");
        mexico = new Room("in Mexico");
        india = new Room("in India");
        spain= new Room("in Spain");
        
        // add rooms to rooms array
        rooms[0] = japan;
        rooms[1] = italy;
        rooms[2] = mexico;
        rooms[3] = india;
        rooms[4] = spain;
        
        // initialise room exits
        japan.setExit("east", italy);
        japan.setExit("south", mexico);
        
        italy.setExit("east", portalRoom);
        italy.setExit("south", india);
        italy.setExit("west", japan);

        mexico.setExit("north", japan);
        mexico.setExit("east", india);

        india.setExit("north", italy);
        india.setExit("east", spain);
        india.setExit("west", mexico);

        spain.setExit("north", portalRoom);
        spain.setExit("west", india);
        
        // initialise directions to all other rooms
        japan.setDirections("Italy: go east.\nMexico: go south.\nIndia: go south, go east.\nSpain: go south, go east, go east.\nPortal: go east, go east.");
        italy.setDirections("Japan: go west.\nMexico: go south, go west.\nIndia: go south.\nSpain: go south, go east.\nPortal: go east.");
        mexico.setDirections("Japan: go north.\nItaly: go north, go east.\nIndia: go east.\nSpain: go east, go east.\nPortal: go north, go east, go east.");
        india.setDirections("Japan: go north, go west.\nItaly: go north.\nMexico: go west.\nSpain: go east.\nPortal: go north, go east.");
        spain.setDirections("Japan: go west, go west, go north.\nItaly:go west, go north\nMexico: go west, go west.\nIndia:go west.\nPortal: go north.");
        
        // add items and wieghts of items to the rooms
        japan.addItem("sushi",10);
        japan.addItem("pasta",10);
        japan.addItem("kebab",10);
        
        italy.addItem("pretzel",10);
        italy.addItem("pizza",10);
        italy.addItem("shoe",100);
        
        mexico.addItem("button",100);
        mexico.addItem("spaghetti",10);
        mexico.addItem("tacos",10);
        
        india.addItem("biryani",10);
        india.addItem("ramen",10);
        india.addItem("burrito",10);
        
        spain.addItem("poutine",10);
        spain.addItem("crayon",100);
        spain.addItem("paella",10);
        
        //add a power to a random room
        getRandomRoom().addItem("power",0);
        
        //add characters to rooms
        fairy = new Character(japan);
        dragon = new Character(spain);
        
        // set the clues for each room
        fairy.addSpeech(japan, "This dish contains raw fish,\nwrapped in seaweed or rice,\nit always tastes so nice.");
        fairy.addSpeech(italy, "This is something that smells cheesy,\nbut it's not a pair of socks,\nit is a food that is sliced,\nand delivered in a box.");
        fairy.addSpeech(mexico, "This has a hard or soft shell,\nwith meat to eat,\nyou can top it with lettuce,\nor top it with cheese.");
        fairy.addSpeech(india, "This is a dish of meat and rice,\nand it conains a lot of spice.");
        fairy.addSpeech(spain, "With rice, seafood, veg and chicken,\nthis dish is made for sharing,\nit is cooked and server all in one pan,\ntry and finish it if you can.");
        
        // set the facts for each room
        dragon.addSpeech(japan, "In Japan KFC is a traditional Christmas eve meal.");
        dragon.addSpeech(italy, "In Italy salad is eaten after the meal (for dessert).");
        dragon.addSpeech(mexico, "The Caesar Salad was invented in Mexico.");
        dragon.addSpeech(india, "The Indian cuisine is the oldest cuisine in human history.");
        dragon.addSpeech(spain, "In Spain there is a New Years custom where you eat 13 grapes.");
        
        currentRoom = japan;  // start game in japan
    }

    /**
     *  Main play routine. Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("░██╗░░░░░░░██╗███████╗██╗░░░░░░█████╗░░█████╗░███╗░░░███╗███████╗");
        System.out.println("░██║░░██╗░░██║██╔════╝██║░░░░░██╔══██╗██╔══██╗████╗░████║██╔════╝");
        System.out.println("░╚██╗████╗██╔╝█████╗░░██║░░░░░██║░░╚═╝██║░░██║██╔████╔██║█████╗░░");
        System.out.println("░░████╔═████║░██╔══╝░░██║░░░░░██║░░██╗██║░░██║██║╚██╔╝██║██╔══╝░░");
        System.out.println("░░╚██╔╝░╚██╔╝░███████╗███████╗╚█████╔╝╚█████╔╝██║░╚═╝░██║███████╗");
        System.out.println("░░░╚═╝░░░╚═╝░░╚══════╝╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚══════╝");

        System.out.println("Welcome to the Magical House!");
        System.out.println("In this house of magic each room is a different country.");
        System.out.println("Your mission is to go home.");
        System.out.println("In order to go home you must go to each country and take their traditional food.");
        System.out.println("But the total weight you can carry is " + player.getMaxWeight());
        System.out.println("You must find and gain power item to be able to carry more weight.");
        System.out.println("On your mission you will be accompanied by a fairy, who you can ask for clues.");
        System.out.println("And you may also run into a dragon, who you can ask for facts about the country you are in.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getBagString());
        isFairy(currentRoom);// prints a message if there is a fairy in the first room
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        boolean hasWon = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            if(command.hasSecondWord()&&command.getSecondWord().equals("home"))
            {
                hasWon=goHome();//signal that player has won. method returns a boolean.
            }
            else{
                goRoom(command);
            }
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")) {
            goBack();
        }
        else if (commandWord.equals("take")) {
            takeItem(command);
        }
        else if (commandWord.equals("return")) {
            returnItem(command);
        }
        else if (commandWord.equals("ask")) {
            if(fairy.inRoom(currentRoom)||dragon.inRoom(currentRoom)){
                ask(command);//user can only use ask command if there is a character in the room.
            }
            else{
                System.out.println("There is no one to ask in this room");
            }
        }
        else if (commandWord.equals("directions")) {
            printDirections(currentRoom);
        }
        else if (commandWord.equals("map")) {
            map();
        }
        else if (commandWord.equals("bag")) {
            bag();
        }
         else if (commandWord.equals("gain")) {
            if(!currentRoom.containsItem("power")){
                System.out.println("There is nothing to gain in this room");
            }
            else{
                gain(command);//user can onlt use the gain command if there is something to gain in the room
            }
        }
        
        // else command not recognised.
        
        return (wantToQuit||hasWon); // returns signal to quit if the user wants to quit or if the player has won the game.
    }
    
    // implementations of user commands:
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the Magical House.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());//diplays all the command words
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. If the next room is the
     * portalRoom then execte the portal method.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if(nextRoom == portalRoom)
        {
            portal();
            return;
        }
        
        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom.push(currentRoom);//pushes the previous room onto the stack
            currentRoom = nextRoom;
            changeFairyRoom();
            changeDragonRoom();
            
            newRoomText();
        }
    }
    
    /** 
     * Checks if the user has won the game. If they have a message is 
     * printed out and the game terminates. If they have not won a message is 
     * printed and the game continues.
     * @return won True if they have won the game, False otherwise.
     */
    private boolean goHome()
    {
        boolean won = false;
        if (player.hasWon()){
            System.out.println("Congratualations you have completed your mission and won the game.");
            won = true;
        }
        else
        {
            System.out.println("You have not completed the mission yet, keep trying.");
        }
        return won;
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * Takes the player to the previous room that they were in.
     */
    private void goBack()
    {
        if(!previousRoom.empty()){
            currentRoom = previousRoom.pop();//pops previous room from top of stack
            System.out.println(currentRoom.getLongDescription());
        }
        else{
            System.out.println("You have gone back to the first room you were in, you can't go back any more.");
        }
    }
    
    /** 
     * Takes an item from the current room of the player 
     * and puts it in the players bag.
     * The player can't take the item if the item is not in the room, 
     * the item is not food, the item is power, or the players bag is full.
     */
    private void takeItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if the command doesn't have a second word, we don't know what the player wants to take.
            System.out.println("Take what?");
            return;
        }
        String item = command.getSecondWord();
        if(!currentRoom.containsItem(item))
        {
            System.out.println("You cannot take this item because it is not in the room.");
        }
        else if(!player.canTake(item)){
            System.out.println("You cannot take this item because it is not food.");
        }
        else if(player.bagFull()){
            System.out.println("You cannot take this item because your bag is full.");
        }
        else if (item.equals("power")){
            System.out.println("You can't take power, you must gain power.");
        }
        else{
            player.addItemToBag(item,currentRoom.getItemWeight(item));
            currentRoom.removeItem(item);
            if(player.bagFull()){
                System.out.println("Your bag is now full. Gain power to be able to take more items.");
                System.out.println("If you have already gained power, you cannot take any more items unless you return some first.");
                System.out.println("If you think you have completed the mission, go home.");
            }
        }
    }
    
    /** 
     *Takes an item from the players bag and returns it to the current room of
     *the player.
     *The player can't return an item if it is not in their bag.
     */
    private void returnItem(Command command)
    {
        if(!command.hasSecondWord()) {
            // if the command doesn't have a second word, we don't know what the player wants to return.
            System.out.println("Return what?");
            return;
        }
        String item = command.getSecondWord();
        if(!player.bagContains(item))
        {
            System.out.println("You cannot return this item because it is not in your bag.");
        }
        else
        {
        currentRoom.addItem(item, player.getItemWeight(item));
        player.returnItemFromBag(item);
        }
    }
    
    /** 
     * Prints out a characters speech if the user inputs a valis command.
     * "ask fairy clue" prints out the fairys speech for the current room.
     * "ask dragon fact" prints out the dragons speech for the current room.
     */
    private void ask(Command command)
    {
        if (!command.hasSecondWord()){
            // if there is no second word, we don't know who to ask.
            System.out.println("Ask who?");
            return;
        }
        
        String secondWord = command.getSecondWord();
        
        // if the second word is not valid
        if(!secondWord.equals("fairy")&& !secondWord.equals("dragon")) { 
            if (fairy.inRoom(currentRoom)&&dragon.inRoom(currentRoom)){
                System.out.println("You must ask the fairy or the dragon.");
                return;
            }
            else if(fairy.inRoom(currentRoom)){
                System.out.println("You must ask the fairy.");
                return;
            }
            else if(dragon.inRoom(currentRoom)){
                System.out.println("You must ask the dragon.");
                return;
            }
            }
        
        // if there is no third word, we don't know what to ask the characters.
        if (!command.hasThirdWord()){
                if(fairy.inRoom(currentRoom)&&secondWord.equals("fairy"))
                {
                    System.out.println("Ask fairy what? ");
                    return;
                }
                else if (!fairy.inRoom(currentRoom)&&secondWord.equals("fairy")){
                    System.out.println("There is no fairy in this room.");
                    return;
                }
                if(dragon.inRoom(currentRoom)&&secondWord.equals("dragon"))
                {
                    System.out.println("Ask dragon what? ");
                    return;
                }
                else if(!dragon.inRoom(currentRoom)&&secondWord.equals("dragon")){
                    System.out.println("There is no dragon in this room. ");
                    return;
                }
            }
        
        String thirdWord = command.getThirdWord();
        
        if(secondWord.equals("fairy")&&!thirdWord.equals("clue")) 
        {
                System.out.println("You must ask the fairy for a clue.");
                return;
        }
        
        if(secondWord.equals("dragon")&&!thirdWord.equals("fact")) 
        {
                System.out.println("You must ask the dragon for a fact.");
                return;
        }
        
        // valid commands: 
        
        if(secondWord.equals("fairy")&&thirdWord.equals("clue")) 
        {
            if (fairy.inRoom(currentRoom))
            {
                System.out.println(fairy.getSpeech(currentRoom));
            }
        }
        
        if(secondWord.equals("dragon")&&thirdWord.equals("fact")) 
        {
            if (dragon.inRoom(currentRoom))
            {
                System.out.println(dragon.getSpeech(currentRoom));
            }
        }
        
    }
    
    /** 
     *Takes a room as a parameter and prints out the directions of how to 
     *get to all the other rooms, from that room.
     *@param room The room that the player is in.
     */
    private void printDirections(Room room)
    {
        System.out.println(room.getDirections());
    }
    
    /** 
     * Prints strings that resemble a map of the game.
     */
    private void map(){
    System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    System.out.println("|                  |                  |                  |");
    System.out.println("|      Japan       |       Italy      |       Portal     |");
    System.out.println("|_ _ _ _ _ _ _ _ _ |_ _ _ _ _ _ _ _ _ |_ _ _ _ _ _ _ _ _ |");
    System.out.println("|                  |                  |                  |");
    System.out.println("|      Mexico      |       India      |       Spain      |");
    System.out.println("|_ _ _ _ _ _ _ _ _ |_ _ _ _ _ _ _ _ _ |_ _ _ _ _ _ _ _ _ |");
    }
    
    /** 
     * Prints out all the items in the players bag and the total weight of all the items.
     */
    private void bag(){
        System.out.println(player.getBagStringLong());
    }
    
    /** 
     * Removes an item from the current room and the player gains the item.
     * "gain power" the power item is removed from the current room and the total weight that the player can 
     * carry increases.
     */
    private void gain(Command command){
        if(!command.hasSecondWord()){
            // if there is no second word, we don't what to gain.
            System.out.println("Gain what? ");
            return;
        }
        
        String powerUp = command.getSecondWord();
        
        if(!powerUp.equals("power") ){
            System.out.println("You must gain the power."); // the player must gain a valid item.
        }
        else{
            currentRoom.removeItem(powerUp);
            player.increaseMaxWeight();
            System.out.println("You have gained power and now you can carry a maximum weight of " + player.getMaxWeight() + ".");
        }
    }
    
    //
    
    /** 
     * Generates a random room by generating a random index for the array 
     * of rooms.
     * @return a random room from the array of rooms.
     */
    private Room getRandomRoom()
    {
        Random r = new Random();
        int n = r.nextInt(rooms.length-1);//gets a random number between 0 and one less than the number of rooms in the rooms array. 
        return rooms[n];// so n can represent any element in the array
    }
    
    /** 
     * Takes the player to a random room 
     * and changes the room that the fairy and dragon are in.
     */
    private void portal()
    {
        portalText();
        
        Room randomRoom = getRandomRoom();
        previousRoom.push(currentRoom);
        currentRoom = randomRoom;
        
        changeFairyRoom();
        
        changeDragonRoom();
        
        newRoomText();
    }
    
    /** 
     * Prints a message that tells the player about the portal.
     */
    private void portalText(){
        System.out.println();
        System.out.println("You are " + portalRoom.getShortDescription());
        System.out.println("You will be transported to a random room.");
        System.out.println();
    }
    
    /** 
     * Prints a message that tells the player about the new room.
     */
    private void newRoomText(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getBagString());
        isFairy(currentRoom);
        isDragon(currentRoom);
    }
    
    /** 
     * Changes the room that the fairy is in to the current room of the player.
     */
    private void changeFairyRoom(){
        fairy.changeRoom(currentRoom);
    }
    
    /** 
     * Randomly changes the room that the dragon is in.
     */
    private void changeDragonRoom(){
        Room randomRoom = getRandomRoom();
        dragon.changeRoom(randomRoom);
    }
    
    /** 
     * Checks if there is a fairy in the currentRoom that the player is in.
     * A statement is printed out to the user if there is a fairy in the current room
     * otherwise nothing happens.
     * @Param room The room that you check if the fairy is in.
     */
    private void isFairy(Room room){
        if(fairy.inRoom(currentRoom)){
                System.out.println("There is a fairy in this room.");
            }
    }
    
    /** 
     * Checks if there is a dragon in the currentRoom that the player is in.
     * A statement is printed out to the user if there is a dragon in the current room
     * otherwise nothing happens.
     * @Param room The room that you check if the dragon is in. 
     */
    private void isDragon(Room room){
        if(dragon.inRoom(currentRoom)){
                System.out.println("There is a dragon in this room.");
            }
    }
}
