/**
 * Filename: Ball.java
 * Description:
 * Author: Wengel Gemu
 * Date: February 27, 2020
 */

import java.text.DecimalFormat;

public class Ball {

  /**
   *  Class field definintions go here
   *    CONSTANTS first
   */
   private static final double BALL_RADIUS          = 4.45;    // radius in inches as given by the problem
   private static final double BALL_WEIGHT          = 1.0;     // weight in pounds as given
   private static final double FRICTION_COEFFICIENT = 0.99;    // one percent slowdown per second due to friction
   public  static final int    X_INDEX              = 0;
   public  static final int    Y_INDEX              = 1;

   // instance variables
   private boolean  isInBounds      = true;                    // all balls will start in bounds by default
   private double[] centerLocation  = new double[2];           // array containing both coordinate values
   private double[] currentSpeed    = new double[2];           // array containing both direction speed values


  /**
   * Constructor to make a new Ball
   *  @param xLocation double-precision value of the X location of the ball
   *  @param yLocation double-precision value of the Y location of the ball
   *  @param xSpeed double-precision value for the initial speed of the ball in X direction
   *  @param ySpeed double-precision value for the initial speed of the ball in Y direction
   */
  
   public Ball(double xLocation, double yLocation, double xSpeed, double ySpeed) {
      // TODO: your code goes here
      this.centerLocation[X_INDEX] = xLocation;
      this.centerLocation[Y_INDEX] = yLocation;
      this.currentSpeed[X_INDEX] = xSpeed;
      this.currentSpeed[Y_INDEX] = ySpeed;
   }

  /**
   *  method to fetch the current speed of the ball
   *  @return  double-precision two-element array of X and Y speed values
   */

   public double[] getCurrentSpeed() {
      return currentSpeed;
   }

  /**
   *  method to fetch the current position of the ball
   *  @return  double-precision two-element array of X and Y location values
   */

   public double[] getCurrentPosition() {
      return centerLocation;
   }

  /**
   *  method to determine if the ball is still moving
   *  @return  boolean true if ball is moving, false if at rest
   *  Note:    at rest is defined as speed <= 1.0 inch/second
   */

   public boolean isStillMoving() {

      // System.out.println("isstillmoving call");
      if ( (currentSpeed[X_INDEX] > 1/12.0) || (currentSpeed[X_INDEX] < -1/12.0) ) {
         // System.out.println("true call");
         return true;
      } 
      // System.out.println("false call");
      return false;
   }

  /**
   *  method to flag the ball as "out of bounds" which will set its speed to zero and its
   *    "isInBounds" value to false so it will effectively no longer be in the simulation
   *  @param fieldWidth    double-precision value of 1/2 the designated field width
   *  @param fieldHeight   double-precision value of 1/2 the designated field height
   */

   // LEARN TO SET BALLOUTOFBOUNDS
   public void setBallOutOfBounds(double fieldWidth, double fieldHeight) {
      // TODO: your code goes here
      if( ! (Math.abs(centerLocation[X_INDEX]) <= (fieldWidth/2.0) && Math.abs(centerLocation[Y_INDEX]) <= (fieldHeight/2.0)) ){
         isInBounds = false;
      }
   }

  /**
   *  method to update the speed of the ball for one timeslice, using FRICTION!!
   *  @param timeSlice     double-precision value of time slace for simulation
   *  @return  double-precision two element array of new velocity values
   */

   public double[] updateSpeedsForOneTick(double timeSlice) {
      // System.out.println("update speed call");
      currentSpeed[X_INDEX] *= Math.pow(0.99, timeSlice);
      currentSpeed[Y_INDEX] *= Math.pow(0.99, timeSlice);
      return currentSpeed;
   }

  /**
   *  method to update the ball's position and velocity
   *  @param timeSlice     double-precision value of time slice for simulation
   */

   public void move(double timeSlice) {
      // TODO: your code here
      centerLocation[X_INDEX] += currentSpeed[X_INDEX];
      centerLocation[Y_INDEX] += currentSpeed[Y_INDEX];
      updateSpeedsForOneTick(timeSlice);

   }

  /**
   * our venerable "toString()" representation
   *  @return  String-y version of what this Ball is
   */
  
   public String toString() {
      DecimalFormat dfp = new DecimalFormat("#0.00");
      DecimalFormat dfv = new DecimalFormat("#0.0000");
      String output = "position <" + dfp.format(centerLocation[X_INDEX]) + ", " +
                                     dfp.format(centerLocation[Y_INDEX]) + ">";
      if (output.indexOf(">") <= 23) {
         output += "\t";
      }
      if (!isInBounds) {
         output += "\t<out of bounds>";
      } else if (1.0 > Math.abs(this.currentSpeed[X_INDEX] * 12.0)) {
         output += "\t<at rest>";
      } else {
         output += "\tvelocity <" + dfv.format(currentSpeed[X_INDEX]) + " X and " +
                                    dfv.format(currentSpeed[Y_INDEX]) + " Y> ft/sec";
      }
      return output;
   }
   public static void main(String[] args) {
   }

}
