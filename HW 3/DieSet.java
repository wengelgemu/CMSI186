/**
 * A dice set holds a collection of Die objects. All of the die objects have
 * the same number of sides.
 */

import java.util.List;
import java.util.ArrayList;

public class DiceSet {

    // TODO add fields
    int sidesOnEachDie;
    int numberOfDice;
    Die[] dieArray;
    String descriptor;
    int sum;

    /**
     * Creates a DiceSet containing the given number of dice, each with the
     * given number of sides. All die values start off as 1. Throws an
     * IllegalArgumentException if either less than two dice were provided
     * or if it is asked to make dice with less than 4 sides.
     */
    public DiceSet(int sidesOnEachDie, int numberOfDice) {

        // TODO
        this.sidesOnEachDie = sidesOnEachDie;
        this.numberOfDice = numberOfDice;
        this.dieArray = new Die[numberOfDice];


        if (numberOfDice < 2) {
            throw new IllegalArgumentException("At least two dice required");
        }
        if (sidesOnEachDie < 4) {
            throw new IllegalArgumentException("Dice must have at least four sides");
        }
        for (int n = 0; n < numberOfDice; n++) {
            dieArray[n] = new Die(sidesOnEachDie, 1);
        }
    }

    /**
     * Creates a DiceSet where each die has the given number of sides, with the
     * given values.
     */
    public DiceSet(int sidesOnEachDie, int... values) {
        // TODO
        this.sidesOnEachDie = sidesOnEachDie;
        this.numberOfDice = values.length;
        this.dieArray = new Die[numberOfDice];

        for (int n = 0; n < numberOfDice; n++) {
            dieArray[n] = new Die(sidesOnEachDie, values[n]);
            // System.out.println("dieArray" + dieArray[n]);
        }

        if (numberOfDice < 2) {
            throw new IllegalArgumentException("At least two dice required");
        }
        if (sidesOnEachDie < 4) {
            throw new IllegalArgumentException("Dice must have at least four sides");
        }
        for (int n = 0; n < numberOfDice; n++) {
            if (values[n] > sidesOnEachDie) {
                throw new IllegalArgumentException("Die value not legal for die shape");
            }
        }
    }

    /**
     * Returns the sum of the values of each die in the set.
     */
    public int sum() {
        // TODO
        for(int n = 0; n<dieArray.length; n++){
            sum+=dieArray[n].getValue();
        }
        return sum;
    }

    /**
     * Rolls all the dice in the set.
     */
    public void rollAll() {
        // TODO
        for(int n = 0; n<numberOfDice; n++){
            dieArray[n].roll();
        }
    }

    /**
     * Rolls the ith die, updating its value.
     */
    public void rollIndividual(int i) {
        // TODO
        dieArray[i].roll();
    }

    /**
     * Returns the value of the ith die.
     */
    public int getIndividual(int i) {
        // TODO
        return this.dieArray[i].getValue();
    }

    /**
     * Returns the values of each of the dice in a list.
     */
    public List<Integer> values() {
        // TODO
        List<Integer> dieList = new ArrayList<Integer>();
        for(int n = 0; n < numberOfDice; n++){
            dieList.add(n,dieArray[n].getValue());    
            // System.out.println("DIE ARRAY VALUE" + dieArray[n].getValue());
            // // System.out.println("HI");
        }
        return dieList;
    }

    /**
     * Returns the descriptor of the dice set; for example "5d20" for a set with
     * five dice of 20 sides each; or "2d6" for a set of two six-sided dice.
     */
    public String descriptor() {
        // TODO
        String stringNum = Integer.toString(numberOfDice);
        String stringSides = Integer.toString(sidesOnEachDie);
        // System.out.println(stringNum+"d"+stringSides);
        descriptor = stringNum+"d"+stringSides;
        //String IntString = Integer.descriptor()
        return descriptor;
    }

    /**
     * Returns a string representation in which each of the die strings are
     * joined without a separator, for example: "[2][5][2][3]".
     */
    public String toString() {
        String dieString = "";
        for (int i = 0; i < numberOfDice; i++) {
            dieString = dieString + "[" + dieArray[i].getValue() + "]";
        }
        return dieString;
    }

    /**
     * Returns whether this dice set has the same distribution of values as
     * another dice set. The two dice sets must have the same number of dice
     * and the same number of sides per dice, and there must be the same
     * number of each value in each set.
     */
    public boolean isIdenticalTo(DiceSet dieArray) {
     if (dieArray.values().containsAll(values())){
         return true;
     }
     else {
         return false;
     }
    }
}
