/*
 * Programming Fundamentals
 * Darcy Ricetto
 * Major Assignment Java - Horce Race
 * Submitted by: Pranav Kural - 200333253
 */
package horseracetester;
/**
 *
 * @author Pranav Kural
 */
public class Die {
//    Class variables - declaration/ initialization
    
    private final int MAX = 6;      // declaring a constant for maximum value of die
    private int sumValue;           // holds the sum of the rolls
    private int num1;               // holds the result of first die
    private int num2;               // holds the result of second die
    
//     Constructor to set the sumValue to the start of die
    public Die(){
        sumValue = 1;
    }
    
/**
 * ---------------- rollDice() ------------------------------------------------\
 * -- 1. Generates two random number between 1 to 6 (inclusive)
 * -- 2. Calculate the sum of these numbers.
 * -- 3. return the result.
 * -- 4. checks for is doubles using isDoubles method in the HorseRace class
 * @return integer sumValue
 */
    public int rollDice(){
        num1 = (int)(Math.random() * MAX) + 1; // getting random number 1
        num2 = (int)(Math.random() * MAX) + 1; // getting random number 2 
        HorseRace.isDoubles(num1, num2);       // Validating for doubles
        sumValue = num1 + num2;                // Calculating the sum
        return sumValue;                       // returning the sum
    }
    
//    Set method to set the sumvalue(indeed the number os steps the horse will take)
    public void setSumValue(int value){
        sumValue = value;
    }
    
//    Get method to get the value of sumValue
    public int getFaceValue(){
        return sumValue;
    }
    
//     Override the string representation of the die class object
    @Override
    public String toString(){
        return String.format("%s: %d, %s: %d, %s: %d", 
                "Die One", num1,
                "Die Two", num2,
                "Total Moves", sumValue);
    }
//    End of Die Class.
}

