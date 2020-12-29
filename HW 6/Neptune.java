// import java.util.Arrays;
/**
 *  File name     :  Neptune.java
 *  @author       :  Wengel Gemu
 *  Date          :  April 12, 2020
 *  Description   :  Part 2 of BigInt lab
 */

 public class Neptune {

    BigInt accelerate;
    

    public Neptune(String... args){
        BigInt accelerate = new BigInt(args[0]);

    }

    public BigInt spacecraft() {
        BigInt seconds = new BigInt("0");
        BigInt distance = new BigInt("0");
        BigInt maxDistance = new BigInt("8800000000000");
        BigInt neptuneVel = new BigInt("23500");

        // earths starting velocity
        BigInt currentVelocity = new BigInt("11186");
        // accelerate by commandline factor every second
        // max speed is half the speed of light
        BigInt maxVelocity = new BigInt("49896229");

        // startingVelocity must be no more than maxVelocity
        while(currentVelocity.compareTo(maxVelocity) == -1){
            currentVelocity = currentVelocity.add(accelerate);
            distance = distance.add(currentVelocity);
            seconds = seconds.add(BigInt.ONE);
        }
        while(distance.compareTo(maxDistance) == -1){
            distance = distance.add(currentVelocity);
        }

        // deccelerating
        while(currentVelocity.compareTo(neptuneVel) == 1){
            currentVelocity = currentVelocity.add(accelerate);
            distance = distance.subtract(currentVelocity);
            seconds = seconds.add(BigInt.ONE);
        }


        return (seconds.add(seconds));
        
    }
    
    public static void main(String[] args) {
        Neptune test = new Neptune(args);
        System.out.println(test.spacecraft());
    }
 }
