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
public class Horse {
//     Class variables - declaration/ initialization
    
    private int horseNumber;        // the horse number
    private int horsePosition;      // the horse position on track
    private String horseName;       // horse/player's name
    
 
    /**
     * All the characteristics of the horse object
     * and their set and get methods, as well as the constructor to initiate them.
     * @param number
     * @param position
     * @param name 
     */
    
    
//    Creating the constructor for the Horse/Player
    public Horse(int number, int position, String name){
        horseNumber = number;
        horsePosition = position;
        horseName = name;
    }
    
    
    
    
    
//    Set method to set Number of the Horse
    public void setNumber(int HorseNum){
        horseNumber = HorseNum;
    }
    
//    Get method to get the number of the horse
    public int getNumber(){
        return horseNumber;
    }
//    Set method to set the horse starting position
    public void setPos(int startPos){
        horsePosition = startPos;
    }
    
//    Get method to get the current position of the horse
    public int getPos(){
        return horsePosition;
    }
    
//    Set method to set name of the horse
    public void setName(String HorseName){
        horseName = HorseName;
    }
    
//    Get method to get the name of the horse
    public String getName(){
        return horseName;
    }
    
//    Overriding the toString Method of class Horse to return the Horse name
    @Override
    public String toString(){
        return getName();
    }
    // End of Horse Class.
}
