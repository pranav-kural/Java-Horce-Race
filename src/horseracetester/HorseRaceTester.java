/*
 * Programming Fundamentals
 * Darcy Ricetto
 * Major Assignment Java - Horce Race
 * Submitted by: Pranav Kural - 200333253
 */
package horseracetester;

import java.util.Scanner;

/**
 *
 * @author pRaNaV kUrAl
 */
public class HorseRaceTester {
//    Declaing/Initiating variables
    
    /* ------------------------------------------------|
     * -------- Variable to work with timing ---------*/
    private static long startTime;      // holds the starting time of test
    private static long endTime;        // holds the ending/finishing time of test
    private static long elaspedTime;    // holds the time it took for the user to win a game (in nanoseconds)
    private static double playTime;     // holds the time taken for a single gameplay (in seconds)
    private static int playerNum;       // Number of players
    
//    Creating a Scanner object named 'input' to take values through keyboard
    private final static Scanner input = new Scanner(System.in);
    
//    Variable to hold the value of input made by user
    private static String enter;
    
//    Declaring an object of class HorseRace
    private static HorseRace race;      
    
    
    /**
     * @param args the command line arguments
     * The Main Method
     */
    public static void main(String[] args) {
        
        /** -------------------------------------------------------------------\
         * The Horse Race Game has been divided into four main parts based on 
         * different functions to be performed or flow of the game.
         *
         * 1. Welcome Users > 
         * 2. Race >
         * >> 2.1 Ask for Number of Players(Create Players) > 
         * >> 2.2 Race(Start race and continue race and start time counter) > 
         * >> 2.3 Stop Game when any player wins( and stop time counter) > 
         * 3. Time Counter (count the total time taken in seconds >
         * 4. Display Result
         *
         * For cleaner, more readable and easily maintainable/scalable code
         * each of the parts are being managed, handled and executed as parts of
         * separate methods. 
         * 
         * The first two parts, Welcoming and Starting the race, are called off
         * through the main method itself, whereas other methods are executed
         * more as inner parts of other methods, being called off within other
         * methods.
         * -------------------------------------------------------------------*/
        

//         Welcoming the player(s)
        WelcomeUsers();
        
//        Starting the Horse Race
        gamePlay();
    }
    
    
/* --------------- 1. WelcomeUsers() Method -----------------------------------\
 * ----- Prints out the Welcome Message to the Users/viewers. -----------------|
 * ---------------------------------------------------------------------------*/
    public static void WelcomeUsers(){
        System.out.println("Welcome! To the gretest ever Horse Race - The Java Horse Race: Star(*) Wars Edition");
        System.out.println("Brought to you by: pRANaV kUraL");
        System.out.println("Let's Start this awesome game\n");
    }
     
/* ----------------------------------------------------------------------------\
 * ---------------- 2. Horse Race ---------------------------------------------|
 *   2.1. Aks user for number of players.
 *   2.2. Print out the start positions
 *   2.3. Start the startTime time counter variable.
 *   2.4. Start the game using while loop, where condition is true until some one
 *          from the players win the game.
 *   2.5. Record the end time in the endTime variable.
 *   2.6. Print out the result of timings.
 * ---------------------------------------------------------------------------*/
    public static  void gamePlay() {
//        First step : asking for number of players
        NumOfPlayers();
        
//        Second step: printing out starting positions
        race.result();
        
//        Third step: Starting the time counter
        startTime = System.nanoTime(); // starting time of the test
        
        
//        Fourth step: The real 'RACE'.

        /** Concept/Usage of status variable.
         * 
         * Using a String as a way to develop a system that would check whether
         * the functions underlying it's loop are required to be run or not.
         * How it Works? :
         * 
         * 1. Main race mechanism has been constituted in the HorseRace Class.
         * 2. There is a String variable named 'status' in the HorseRace class.
         *    It is set to 'public' modifier, to enables it's access in this 
         *    class without object initiation.
         * 3. It's value is pre-defined to 'continue'.
         * 4. When ever any player reaches the finish line, the value of 'status'
         *    variable is changed to 'stop'.
         * 5. While the game is being run, the value of 'status' variable in the
         *    HorseRace class is continuously monitored to check whether the 
         *    game is to be continued or stop.
         * 6. For each time the while loop works, it checks if the value of the
         *    status variable is equal to the value of the 'continue'.
         * 7. When a player wins, value of 'status' variable in the HorseRace 
         *    class gets changed to 'stop', hence the condition of while loop gets
         *    false('stop'.equals('continues')) = false, therefore the game stops, 
         *    and the program continues with the following steps.
         *
         */
        
//        While: checks if both variables are equal,i.e., equal to 'continue', 
//        then it continues the statements inside the while loop, otherwise
//        jumps to statements below it.
            while (HorseRace.status.equalsIgnoreCase("continue")) {
        /* --------------------------------------------------------------------\
         *   for loop: the purpose of this for loop is to revolute through the
         * - inclosed statements for each player for at least once.
         * - Example: for playerNum = 4; it will run the statements inside it
         * - four times, at least once for each of the playerNum's values.
         * - */
            for (int i = 0; i < playerNum; i++) {
                
                /** -----------------------------------------------------------\
                 * Checks the continuity of the the race, in case any player 
                 * wins before the while loop being executed. e.g., If a player
                 * wins before a single game session ends.
                  -----------*/
                if (HorseRace.status.equalsIgnoreCase("continue")) {
                    
                /** -----------------------------------------------------------\
                 * Asks the player to enter 'R' or 'r' to Roll the dice, 
                 * takes in input. Runs the game input passes validation.
                  -----------*/
                    System.out.print("\nPlayer Number " + (i + 1) + " Please enter 'R' to roll the dices: ");
                    enter = input.next();
                    
                    if (enter.equalsIgnoreCase("R")) {
                    /** -------------------------------------------------------\
                     * Calls the race(), method of the HorseRace 
                     * Passing in the number of player being approached
                     * when the statements get executed.
                     * -----------*/
                        race.race(i);
                    }
                }
            }
        } 
        
//        Fifth step: Record the endTime of the game;
        endTime = System.nanoTime(); // time when the test ends
        
//        Sixth and the Last step: Display out the result related to time taken.
        System.out.println("You won in " + gamePlayTime() + " seconds");
    }
       
/* ----------------------------------------------------------------------------\
 * ---------------- 2.1 Aksing User for number of Players ---------------------|
 *   1. Prints out statement asking user to enter number of player
 *   2. uses Scanner Object 'input' with method nextInt() to get an Integer input
 *   3. Create new HorseRace class objects using the int input by user, i.e.,
 *      based on the number of players.
 * ---------------------------------------------------------------------------*/    
    public static void NumOfPlayers(){
        // Asking User for number of Players(horses)
        System.out.print("How Many Players will be playing this game: ");
        playerNum = input.nextInt(); // ask user to enter a number
        race = new HorseRace(playerNum); // creates the HorseRace object, and 
//        sends out the number of players required to the HorseRace constructor.
    }

    
    
    
    /** -----------------------------------------------------------------------\
     * ---------------------------- 3. gamePlayTime ---------------------------|
     *   1. Calculate the time elapsed in the game.
     *   2. It records in NanoSeconds, based on System time, not the User's 
     *      computer time clock, for more accuracy.
     *   3. Then the time in nanoseconds is converted to seconds.
     *   4. Finally time is rounded off to 2 decimal spaces before returning.    
     * @return double -*/    
    public static double gamePlayTime()
    {
        // Calculate time taken for the playing in nanoseconds
        elaspedTime = endTime - startTime;
        
        // Calculate the time taken for the playing in seconds
        playTime = elaspedTime / 1e9; // converting time in nanoseconds to seconds
        
        // returns the time rounded off to 2 decimal places
        return Math.round((playTime * 100)/100);
    }
}
