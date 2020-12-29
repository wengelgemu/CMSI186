/**
 * Filename: SoccerSim.java
 * Description:
 * Author: Wengel Gemu
 * Date: February 29, 2020
 */

import java.text.DecimalFormat;
import java.util.Arrays;

public class SoccerSim {

  /**
   *  TODO: Class field definintions go here
   *    CONSTANTS first
   */

   // TODO: instance variables
   private static int numOfBalls = 0;
   private static Ball[] ballsArray = null;
   public static double timeslice = 1.0 ;
   public static int numIterations = 0;
   // public static double xLocation;
   // public static double yLocation;
   // public static double xSpeed;
   // public static double ySpeed;
   private static int plays;

  /**
   * Constructor to create a new soccer simulation with a field, soccer balls, and (optional) pole
   * Are there zero arguments? Are there 4 args per ball? Can the args be converted to doubles?
   * Are the args within range? Are they in the field?
   * @param args String array of the arguments supplied to the program
   */
   public SoccerSim(String... args) throws NumberFormatException, IllegalArgumentException {
     if(0 == args.length){
        throw new IllegalArgumentException("At least four arguments required (xPos, yPos, xSpeed, ySpeed)");
     }
     if(1 == args.length){
        throw new IllegalArgumentException("Requires 4 args per ball");
     }
     if(2 == args.length){
        throw new IllegalArgumentException("Requires 4 args per ball");
     }
     if(3 == args.length){
        throw new IllegalArgumentException("Requires 4 args per ball");
     }
     if(6 == args.length){
        throw new IllegalArgumentException("Requires 4 args per ball");
     }
     plays = (int)(args.length/4.0);
     ballsArray = new Ball[plays];
     int j = 0;
     for (int i = 0; i < plays*4; i+=4){
        Double xLocation = Double.parseDouble(args[i+0]);
        Double yLocation = Double.parseDouble(args[i+1]);
        Double xSpeed = Double.parseDouble(args[i+2]);
        Double ySpeed = Double.parseDouble(args[i+3]);
        ballsArray[j] = new Ball(xLocation, yLocation, xSpeed, ySpeed);
        j++;
        numOfBalls = j;
      }
      // System.out.println(Arrays.toString(ballsArray));
      if (args.length % 4 == 1) {
         timeslice = Double.parseDouble(args[args.length-1]);
         if (timeslice < 0){
            throw new IllegalArgumentException("Timeslice must be positive");
         }
      }
   }

  /**
   *  Method to move the balls in the soccerBall array by 1 timeslice
   *  Set ball out of bounds depending on the new location.
   */
   public void simUpdate() {
      numIterations++;
      for (int i=0; i < ballsArray.length; i++){
         ballsArray[i].move(timeslice);
         ballsArray[i].setBallOutOfBounds(1000, 1000);
      }
      System.out.println(numIterations);
   }

  /**
   *  Method to check if any balls collided with each other (or a pole)
   *  If so, the simulation is over.
   *  @return int array of ballsCollided
   */
   public int[] collisionCheck() {
      int[] ballsCollided = new int[2];
      for (int i = 0; i < ballsArray.length-1; i++) {
         for (int j = i + 1; j < ballsArray.length; j++){
            double distance = Math.sqrt( Math.pow( (ballsArray[0].getCurrentPosition()[0] - ballsArray[1].getCurrentPosition()[0]), 2 ) + Math.pow( (ballsArray[0].getCurrentPosition()[1] - ballsArray[1].getCurrentPosition()[1]), 2 ));
            if (distance <= (8.9/12)){
               ballsCollided[0] = i;
               ballsCollided[1] = j;
               return ballsCollided;
            }
         }
      }
      return null;
   }

  /**
   *  Method to check whether at least two balls are still moving
   *  If you are NOT doing the optional part with the pole, at least two balls
   *  must be moving in order for there to be a collision. If only one ball is moving,
   *  and there has not yet been a collision, then the simulation is over.
   *  IF YOU ARE ADDING A POLE, ONLY ONE BALL MUST STILL BE MOVING!!!
   *  @return boolean flag (true if at least one ball is moving, else false)
   */
   public boolean atLeastTwoBallsStillMoving() {
      int ballMoved = 0;
      for (int i = 0; i<ballsArray.length; i++){
         if(ballsArray[i].isStillMoving()){
            ballMoved++;
         }
      }
      if (ballMoved > 1){
         return true;
      } else {
         return false;
      }
   }

  /**
   *  Method to run the simulation
   *  @return String report (if there's a collision, and how many iterations)
   */
   public static String runSimulation(String... args) {
      SoccerSim ss = new SoccerSim(args);
      while(ss.atLeastTwoBallsStillMoving()){
         ss.simUpdate();
         int[] collidedBalls = ss.collisionCheck();
         if (collidedBalls == null){
            continue;
         } else {
            // System.out.println("Collision occurred between ball " + collidedBalls[0] + " and ball " + collidedBalls[1] + ".");
            return "Collision occurred between ball " + collidedBalls[0] + " and ball " + collidedBalls[1] + ".";
         }
      }
      // System.out.println("none");
      return "NO COLLISION POSSIBLE!";
  }

  /**
   *  Main method of the simulation
   *  @param  args  String array of the command line arguments
   */
   public static void main(String args[]) {
   //   // TODO: your code goes here
   //   SoccerSim ss = new SoccerSim();
     try {
        runSimulation(args);
     } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
     }
   }
}
