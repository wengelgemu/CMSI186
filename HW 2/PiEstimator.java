/**
 * Filename: PiEstimator.java
 * Description: Java test to estimate value of po
 * Author: Wengel Gemu
 * Date: 2/03/2020
 */

 public class PiEstimator {

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Exactly one argument required");
            }
            int darts = Integer.parseInt(args[0]);
            if (darts < 1) {
                System.err.print("At least one dart required");
            } else {
                System.out.println(estimate(darts));
            }
        } catch (NumberFormatException e) {
            System.err.print("Argument must be an integer");
        } catch (IllegalArgumentException e) {
            System.err.print(e.getMessage());
        }
    }

    public static double estimate(int darts) {
        if (darts < 1) {
            throw new IllegalArgumentException("At least one dart required");
        }
        double x;
        double y;
        int throwDart = 0;
        int hitCircle = 0;
        int i;
        double checkDart;

        for (i = 10000000; i > 0; i--){

            // throw random dart
            x = Math.random();
            y = Math.random();
            throwDart++;

            // check if dart lands in circle
            checkDart = ((x*x) + (y*y));
            if (checkDart <= 1){
                hitCircle++;
            }

        }

        // derive pi using equation
        double piDiv4 = ((double)hitCircle / (double)throwDart);
        double piVal = (4*piDiv4);
        return piVal;
    }
}
