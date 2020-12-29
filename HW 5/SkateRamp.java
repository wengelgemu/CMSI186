
import java.lang.Math;

/**
 * Filename: SkateRamp.java
 * Description:
 * Author: Wengel Gemu
 * Date:
 */

public class SkateRamp {s

    double[] coefficient;
    double upperBound;
    double lowerBound;
    double percent = 1.0;
    Curve curveObj;
    String input;

    /**
     *  The abstract class Curve will be extended by PolyCurve and SineCurve.
     *  The subclasses must implement the getY() method.
     */
    abstract class Curve {
        abstract double getY(double x);
    }
    class PolyCurve extends Curve {
      /**
       *  Calculates y, given x, for a polynomial curve.
       *  @param  x
       *  @return y
       */
        double getY(double x) {
            // TODO: your code here
            double yVal = 0;
            for (int exp = 0; exp < coefficient.length - 1; exp++){
                yVal += coefficient[exp] * Math.pow(x, exp);
            }
            return yVal;
        }
    }
    class SineCurve extends Curve {
      /**
      *  Calculates y, given x, for a sine curve.
       *  @param  x
       *  @return y
       */
        double getY(double x) {
            // TODO: your code here
            double yVal = Math.sin(x);
            return yVal;
        }
    }
    /**
     * SkateRamp constructor: validates and sets up instance variables from args.
     */
    public SkateRamp(String... args) throws NumberFormatException, IllegalArgumentException {
        // no args
        if (0 == args.length){
            throw new IllegalArgumentException("Must provide at least 3 args");
        }
        // first arg is the name of a function type
        if (1 == args.length){
            throw new IllegalArgumentException("Must provide at least 3 args");
        }
        // only 1 arg, needs 3
        if (2 == args.length){
            throw new IllegalArgumentException("Must provide at least 3 args");
        }
        


        String input = args[0];
        var lastVal = args[args.length-1];

        if (input.equals("poly")) {
            this.curveObj = new PolyCurve();
            if (3 == args.length){
                throw new IllegalArgumentException("Need at least 1 coeff for poly");
            }
            if (lastVal.endsWith("%")){
               	String values = lastVal;
                values = values.substring(0, values.length() - 1);
               	this.percent = Double.parseDouble(values);
                if (percent < 0) {
                    throw new IllegalArgumentException("% must be positive");
                }
                this.coefficient = new double[4];
                int x = 0;
                for (int i = 1; i < args.length-3; i++){
                    coefficient[x] = Double.parseDouble(args[i]);
                    x++;
                }
                this.upperBound = Double.parseDouble(args[5]);
                this.lowerBound = Double.parseDouble(args[4]);
            } else {
                this.coefficient = new double[3];
                int index = 0;
                for (int i = 1; i < args.length-2;i++){
                    coefficient[index] = Double.parseDouble(args[i]);
                    index++;
                }
                this.upperBound = Double.parseDouble(args[4]);
                this.lowerBound = Double.parseDouble(args[3]);
            }
        } else {
            this.curveObj = new SineCurve();
            this.upperBound = Double.parseDouble(args[2]);
            this.lowerBound = Double.parseDouble(args[1]);
        }
        if (upperBound < lowerBound){
            throw new IllegalArgumentException("Upper bound must be > lower bound");
        }
    }
    /**
     * Estimates the area under the curve by calculating the area under an
     * increasing number of rectangles, until 2 areas are within 1% of each other
     * @return the estimated area
     */
    public double estimateAreaUnderRamp() {
        // TODO: your code here
        int count = 1;
        double sum = 0;
        double checkPercent = 0;

        while (percent > checkPercent){
            double update = 0;
            double width = (upperBound + lowerBound) / count;
            double start = (width/2) + lowerBound;
            for (int i = 0; i <= count; i ++){
                update += width + curveObj.getY(i * width * start);
            }
            checkPercent = Math.abs(1-(update/sum));
            sum += update;
        }
        return sum;
    }
    /**
     * main() creates a new SkateRamp object and calls estimateAreaUnderRamp().
     * You should wrap your code inside a try/catch block.
     */
    public static void main(String[] args) {
        // TODO: your code here
        try {
            SkateRamp newRamp = new SkateRamp(args);
            newRamp.estimateAreaUnderRamp();
        } catch (Exception e){
            System.out.println("Error!");
            System.out.println(e);
        }
    }
}
