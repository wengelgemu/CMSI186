import java.util.*;

public class HighRoller {
    public static void main(String args[]){

        DiceSet diceSet = null;

        System.out.println("Welcome to Highroll!");

        var console = System.console();

        while (true) {
            try {
                String command = console.readLine("Enter a command (h for help): ").trim();
                // System.out.println(command);

                if (command.length() == 0){
                    System.out.println("Enter some text!");
                }
                // print a help message
                String h = "h";
                if (command.equals(h) || command.contains("help")){
                // if (command.contains("help")){
                    System.out.println("Enter 'h' or 'help' to print a help message");
                    System.out.println("Enter 'q' or 'quit' to quit the program");
                    System.out.println("Enter use <s> <n> for a new set of dice");
                    System.out.println("Enter roll all: to roll all the dice");
                    System.out.println("Enter roll <i> to roll the ith dice in the set");
                    System.out.println("Enter 'high' or 'highest' to print the highest roll so far");
                }

                // quit the program
                if (command.contains("q") || command.contains("quit")){
                    System.out.println("thanks for playing!");
                    break;
                }

                // new set of dice
                if (command.contains("use")){
                    // split use <s> <n> to array
                    var useArray = command.split(" ");

                    diceSet = new DiceSet(Integer.parseInt(useArray[1]), Integer.parseInt(useArray[2]));

                    // print descriptor
                    System.out.println("you are now using a " + diceSet.descriptor());
                    System.out.println(diceSet.values());

                }

                // roll all the dice
                if (command.contains("roll all")){
                    diceSet.rollAll();
                    System.out.println("You rolled " + diceSet.toString());
                }

                // roll the ith dice in the set
                if (command.contains("roll") ){
                    // split roll <i> to array
                    var rollArray = command.split(" ");
                    diceSet.rollIndividual(Integer.parseInt(rollArray[1]));
                    System.out.println(diceSet.toString());
                    // System.out.println(Arrays.toString(rollArray));
                }

                // print the highest roll so far
                if (command.contains("high") || command.contains("highest")){
                    var highScore = diceSet.sum();
                    System.out.println( "The score for this set is " + highScore);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
