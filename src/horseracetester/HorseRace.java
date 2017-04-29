/*
 * Programming Fundamentals
 * Darcy Ricetto
 * Major Assignment Java - Horce Race
 * Submitted by: Pranav Kural - 200333253
 */
package horseracetester;

import java.util.*;

/**
 *
 * @author Pranav Kural
 */
public class HorseRace {

//     Class variables - declaration/ initialization

    /**
     * Declaring new ArrayList object of class Horse and name 'player'.
     */
    private final ArrayList<Horse> player = new ArrayList<>();
    
    /**
     * String horses[] 
     * 
     * Info: An Array object to hold String data type.
     * 
     * Usage: horses[] is the main variable that stores asterisk marks ( * ) 
     * which indeed represents one step on the race track.
     * 
     * Concept: I basically divided each track into four separate pieces; e.g.
     * 
     * Player x ************|                                   >Finish
     * 
     * First Part: 'Player x ', (x being the number of player)
     * Second Part: '*************', i.e., a String that holds number of stars
     * Third Part: '               ', i.e., a String that holds number of spaces
     * Fourth Part: '>Finish ',i.e., a constant String to represent finish line
     * 
     * Pseudocode for its functioning: 
     * 
     * 1. Player x: The player name is printed out in the the result() method
     * using appropriate object.
     * 
     * 2. horses[] stores the amount of asterisk marks, representing the steps
     * of each horse/player. It increases with each roll, and will go to a 
     * maximum of 249 characters ( '|' is the 250th character ) ( since after 
     * 250 characters a player will win and game will stop).
     * 
     * 3. A local string variable 'spacesRequired' of method result(), stores
     * the amount of spaces to be shown between the stars and the finish line.
     * 
     * 4. '>Finish ', string is added to the result in the method result() 
     *  without use of any extra variable.
     */
        private final String horses[];
        
//Creating an object of class 'Die' named 'Roll' to get the sum after each roll.
    private final Die Roll = new Die();
    
//    variable to hold the total number of players in game( looping purposes )
    private final int x;
    
//    Constant variable to hold the total number steps on track
    private final int totalSteps = 250;
    
//    String variable to hold the status of gameplay, concept explained in the
//    HorseRaceTester Class.
    public static String status = "continue";
    
//    Int Array Object to hold the current sum of rolls(i.e., current move)
    private final int[] rollValue;
    
//    Int Array Object to hold the current position of horses in relation to
//    other horses. Concept explained in displayHorsePosition() method.
    private final int[] horsePos;
    
//    A boolean object to check if doubles happen
    private static boolean doubles;
    
//    Scanner object of name 'input' to take input for next roll
    private final Scanner input = new Scanner(System.in);

//    Creating the constructor taking input of number of horses
    public HorseRace(int playerCount) {
//      ensureCapacity method of ArrayList Object to ensure Object size is appropriate
        player.ensureCapacity(playerCount);
        //pony = new Horse[playerCount];
        
//        Specifiying the size of horses array equal to the number of horses required
        horses = new String[playerCount];
        
        /**
         * For loop: to set the value of each element of array horses and 
         * ArrayList Object player.
         * 
         * It runs for based on the number of players, once for each player.
         * 
         */
        for (int i = 0; i < playerCount; i++) {
//            Initiates each horses element to empty value.
            horses[i] = "";
            
//            Creating a new Horse object for each player.
//            Sending horse characterstics in parameters as: (Number, Position, Name)
            player.add(new Horse(i + 1, 0, "player" + (i + 1)));
            //pony[i] = new Horse(i + 1, horses[i].length(), "Player " + (i + 1));
        }

//        Initiating the value of global variable x to store the num of players
        x = playerCount;
        
//        Initiating the size of rollVale and HorsePos variables equal to the number of players.
        rollValue = new int[playerCount];
        horsePos = new int[playerCount];
    }

    
//    Creating the main race method, (playerNum = number of current player).
    public void race(int playerNum) {

//        Calls the rollDice method in class Die to get sum of rolling two dices
        rollValue[playerNum] = Roll.rollDice();
        
        /**
         * For Loop: 
         * 
         * 1. Loops for a total of x number of times, where x is the sum of roll
         *    called and received through the rollValue array. 
         * 
         * 2. Appends an asterisk mark / star : ( * ), to the specific horses 
         * array, each time the for loop runs. This essentially means one step
         * in terms of the game and horse. Each time for loop runs, the horse
         * moves one step further, and the total number of steps the horse moves
         * depends on the number of times the for loop runs(i.e., the value of
         * rollValue array/ sum of roll).
         * 
         */
        for (int i = 0; i < rollValue[playerNum]; i++) {
            
            /**
             * If Statements: 
             * 
             * 1. If statements are executed if the number of stars in the 
             * referenced horses object is less than 249 (since 250th is the
             * finish position). It adds a " * " to the horses object, meaning
             * horse moves one step ahead.
             * 
             * 2. Else statements: these are executed when the horses object has
             * 249 stars, therefore, it means that the horse is just in front 
             * of the finish line. So, one step further means he will win the 
             * race and the game should be stopped.
             */
            if (horses[playerNum].length() < totalSteps - 1) {
                horses[playerNum] += "*";    // '*' added
            } else {
                horses[playerNum] += "*";    // 250th star added
                System.out.println("Hurray!! Player " + (playerNum + 1) + " Won the Game!");
                status = "stop";            // value of status variable changed
                break;                       // Breaks for loop
            }
        }
        
//        result is printed after each turn, by calling the result method
        result();
        
        /**
         * Facilitating the double case scenario:
         * 
         * If the player gets double, i.e., same numbers on both dices on a roll
         * the player should get another chance.
         * 
         * 
         * If statements: 
         * 
         * 1. double boolean is true only when a double scenario has occurred 
         * during roll.
         * 2. status variable's value is 'continue' only when no body has yet won.
         * 3.If both conditions are true the if statements are executed and the
         * referenced player is given another chance to roll and play.
         */
        if (doubles && status.equalsIgnoreCase("continue")) {
            doubles = false;        // turns doubles back to orginial stage
                                    // to avoid repition
//            Print out a message for the lucky player and ask for another roll
            System.out.print("\nPlayer Number " + (playerNum + 1) + " Press enter 'R' for your second roll/turn (because of doubles): ");
            String inputEnter = input.next();       // input'r' for roll
            
//             checks if the input is valid for a roll
            if (inputEnter.equalsIgnoreCase("R")) {
                race(playerNum);    // calls the race method again for that player
            }
        }
    }

//    The result() method to simply print out the result in required format
    public void result() {
        
//        Print out the result board header portion
        System.out.println("\n-------------------------------------------------------------");
        System.out.println("Game Board");
        System.out.println("=============================================================");

        /**
         * For Loop: 
         * 
         * Calculates the number of spaces required by subtracting the amount of
         * stars in the horses object, hence the number of stars on track, out 
         * of the totalSteps. 
         * 
         * runs once for each player (x = number of players)
         */
        for (int i = 0; i < x; i++) {
            
//            declaring and initiating the spaces variable
            String spaces = "";
            
//            obtaining and storing the number of stars in the horses object
            int stars = horses[i].length();
            
//            calculating the spaces required
            int spacesRequired = totalSteps - stars;
            
//            Appending the required spaces to the spaces variable
            for (int y = 0; y < spacesRequired; y++) {
                spaces += " ";
            }
            
//            printing out the result
            System.out.println(player.get(i) + " " + horses[i] + "|" + spaces + ">Finish");
//            System.out.println(pony[i] + " " + horses[i] + "|" + spaces + ">Finish");
        }
        
//        Calling the displayHorsePosition method to display horse positions
        displayHorsePosition();
    }

//    The diplayHorsePosition method to display horse position in relation to 
//    other horses and the finish line.
    public void displayHorsePosition() {

        /**
         * (This is really tricky part, I had to spent a lot of time on it, I'm
         * not sure if I'll be able to explain it completely what I was trying
         * to do how I achieved it.)
         * 
         * ------------------------ Position of Horse -------------------------\
         * This includes identifying the position of horse in relation to the
         * other horses, and in relation to the finish line.
         * 
         * 
         * Part 1 : Position in relation to finish line. 
         * Since, horse is nothing but the character '|' in front of the last
         * star in every horses[] string, therefore, length of the horses[] 
         * element represent the position of that horse on the track. e.g., 
         * horses[2] = "*****"; 
         * therefore, horse's position on track will be 5th spot,
         * and the steps away from finish will be (totalStep - horses[2].length())
         * 
         * 
         * Part 2: Position in relation to other horses (tricky part)
         * Since, the position of all horses in this game was dependent on the 
         * amount of stars in their respective horses[] array, what needs to be
         * done was, to have a mechanism that would compare all values of horses[]
         * array with each other, and then inform which horses element was at what
         * position in descending order (the horse with most stars/steps will be
         * ahead of all).
         * 
         * To be true, I wasn't able to achieve this. But I tried out and made 
         * this little hack for this part. 
         * 
         * Pseudocode to achieve part 2
         * 
         * 1. Made a new integer array object named y.
         * 2. Using a for loop, set each element of y equal to the steps left for
         * the horse corresponding to it's array index. e.g., for horse number 3 
         * (Since array starts from 0, 3rd horse is with array index 2)
         * 
         *  y[2] = (totalSteps - horses[2].length);
         * 
         * 3. Use Array object's sort method, to sort the array Y (which holds 
         * the steps left to finish line for each horse). So that, the lowest
         * value is at the top, and hence the lowest amount of stars required
         * to reach the finish line is at the top.
         * 
         * 4. Then using a two for loops, check each value of y element in order
         * with each value of horses elements, and when a match is found store
         * it into the horsePos array object. e.g., for 2nd value of element y,
         * which represent the second lowest steps required to finish line, i.e.,
         * represents the horse at second position in relation to others:
         * 
         * if (y[2] == (totalSteps - horses[k].length()))
         *      horsePos[2] = k;
         * 
         * it will match the value of element y[2], with the steps left for each
         * element of horses, and when a match is found, say at k = 4, then
         * 
         *      horsePos[2] = 4;
         * 
         * Now, since 3rd element in the horsePos is equal to 4, it tells that
         * 4 element of the horses array object is at position 3rd in relation to
         * other horses. Simply, the horsePos array is the one now which contains
         * the list of horses array arranged in order of their position in 
         * relation to each other, and it uses only the index of the horses array
         * to reference it's element being on that position. 
         * 
         *  
         */
        
        
        
        
        
//        declaring an int array object of size equal to the size of horses array
        int[] y = new int[horses.length];
        
//        For loop : sets the value of each y element to the number of steps left
        for (int i = 0; i < horses.length; i++) {
            y[i] = (totalSteps - horses[i].length());
        }

        /** -------------------------------------------------------------------\
         *  The flag boolean is used to prevent system from printing out wrong 
         * positions in case some players are yet to start, or when a few players
         * are at the same position.
         */
        
//        A local boolean variable to check if all values of array are same
        boolean flag = false;
//        int variable to check values
        int first = y[0];
//        For loop checks each values of array y against the first int variable
        for (int i = 1; i < y.length; i++) {
            if (y[i] != first) {
//                sets flag boolean true if values are same
                flag = true;
            }
        }
        
//        Sorting the values of array 'y' using sort method of Array object
        Arrays.sort(y);

//        for loop: runs for all values of array y and execute certain statements
//          for each of it's value
        for (int i = 0; i < y.length; i++) {
            
//            for loop: runs for the each element of the horses object, and 
//              executes certain statements for each element of horses object
            for (int k = 0; k < horses.length; k++) {
                
//                if the element of y[i] is equal to the steps left for the player at k element in horses
                if (y[i] == (totalSteps - horses[k].length())) {
                    
//                    then set the value of horsePos[i] equal to the k
                    horsePos[i] = k;
                    
//                    if i has passed zero
                    if (i > 0){
//                        if the horsePos's current value is equal to the last value
                        if (horsePos[i] == horsePos[i-1]){
//                            then trun flag false, hence players have same position
                            flag = false;
                        }
                    }
                }
            }
        }
        
        int z = x-1;
//        if flag is true, i.e., is all players have different positions, otherwise else
        if (flag) {
            for (int i = 0; i < x; i++) {
                System.out.println("Player Number " + (horsePos[i] + 1) + " is at position number " + (i + 1) + " after moving " + rollValue[i] + " steps ahead." + " Steps Covered: " + horses[horsePos[i]].length() + " & Steps Left: " + (totalSteps - horses[i].length()) + " out of " + totalSteps + ".");
            }
        } else {
            if (z < 0) {
                System.out.println("Players are at the same position, continue to see the change...");
            }
            else {
            System.out.println("Players are at starting position, please continue to see the change...");
            z--;
            }
        }
    }

    
//  The isDoubles method to check doubles and therefore set the value of doubles boolean
    public static void isDoubles(int num1, int num2) {
        
//        if result from both dice is equal
        if (num1 == num2) {
            System.out.println("Yippee!! you got doubles, There 4 u got another chance, result from your fisrt chance is: ");
            System.out.println("Die One: " + num1 + " , Die Two: " + num2);
            doubles = true; // set doubles to true, will effect result method().
        }
    }
    
    
//    Set method for Horse Posiition, intakes horse number and desired position
    public void setHorsePos(int horseNum, int newPos) {
        
//        if the desired position is not equal to the actual current position
        if (newPos != horses[horseNum].length()){
            
//            increment the horse position
            for (int i = 0; i < newPos; i++){
                horses[horseNum] += "*";
            }
        }
    }
    
    
    //    Get method for current Horse Posiition, intakes horse number
    public int getHorsePos(int horseNum) {
        return horses[horseNum-1].length(); // horseNum - 1, because array index is 0 based
    }
    
//    End of the HorseRace Class.
}
