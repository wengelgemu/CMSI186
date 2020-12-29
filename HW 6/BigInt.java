import java.sql.Array;
import java.util.Arrays;

/**
 *  File name     :  BigInt.java
 *  @author       :  Wengel Gemu
 *  Date          :  April 2, 2020
 *  Description   :  Code to handle big integers
 */

public class BigInt {


    // TODO: define your static and instance variables here
    public static final BigInt ONE = new BigInt("1");
    public static final BigInt ZERO = new BigInt("0");
    public static final BigInt TEN = new BigInt("10");

    // instance variables
    public String inputVal;
    public int sign = 0;
    public char[] charArray = null;
    public int[] inputArray;
    public byte[] byteArray = null;
    public byte[] reverseByteArray;
    public String str = "";
    public String reverseStr = "";
    public Boolean flag;


    /**
     *  Constructor takes in a string, saves it, checks for a sign character,
     *  checks to see if it's all valid digits, and reverses it for later use.
     *  @param  value  String value to make into a BigInt
     */
     public BigInt(String value) {
        inputVal = value;

        // check for sign, 1 for negative, 0 for positive
        if(inputVal.charAt(0) == '-'){
            sign = 1;
            inputVal = inputVal.substring(1, inputVal.length());
            
        } else if (inputVal.charAt(0) == '+'){
            sign = 0;
            inputVal = inputVal.substring(1, inputVal.length());

        }

        // new method
        // reverse byte array
        charArray = inputVal.toCharArray();
        byteArray = new byte[charArray.length];
        int byteIndex = 0;
        for (int i = (charArray.length-1); i>=0; i--){
            flag = Character.isDigit(charArray[i]);
            if (flag){
                reverseStr = reverseStr + Character.toString(charArray[i]);
                byteArray[byteIndex] = (byte) Character.getNumericValue(charArray[i]);
                byteIndex++;
            } else {
                throw new IllegalArgumentException("Sorry, all characters must be decimal digit or sign characters.");
            }
        }
        charArray = reverseStr.toCharArray();

     }


     /**
      *  Method to add a BigInt value passed in as an argument to this BigInt
      *  @param  otherInt other BigInt to add to this BigInt
      *  @return BigInt that's the sum of this BigInt and the one passed in
      */
      public BigInt add(BigInt otherInt) {
          // TODO: your code here
          String sum = "";
          int carryOver = 0;

          
          byte compare = (byte) this.compareTo(otherInt);

        //   System.out.println(compare);
        //   (compareTo) if input is greater than otherint, compare = 1
          if (compare >= 0){
            //   loop through values byte array
              for (int i = 0; i < this.byteArray.length; i++){
                  if(i >= otherInt.byteArray.length){
                      sum = Integer.toString(this.byteArray[i] + carryOver) + sum;
                  } else {
                    //   carry over values over 9
                      sum = Integer.toString(((this.byteArray[i]) + otherInt.byteArray[i] + carryOver) % 10) + sum;
                      if((this.byteArray[i] + otherInt.byteArray[i] + carryOver) >= 10){
                        carryOver = 1;
                      } else {
                        carryOver = 0;
                      }
                  }
              }

              if (carryOver > 0){
                  sum = Integer.toString(carryOver) + sum;
              }
              //   sign = 1 (negative), sign = 0 (positive)
              if (this.sign == 1){
                  return new BigInt("-" + sum);
              } else {
                  return new BigInt(sum);
              }
          } else {
        //   (compareTo) if input is less than otherint, compare = -1
              for (int i = 0; i < otherInt.byteArray.length; i++){
                  if(i >= this.byteArray.length){
                      sum = Integer.toString(otherInt.byteArray[i] + carryOver) + sum;
                  } else {
                    //   carry over values over 9
                      sum = Integer.toString((this.byteArray[i] + otherInt.byteArray[i] + carryOver) % 10) + sum;
                      if((this.byteArray[i] + otherInt.byteArray[i] + carryOver) >= 10){
                          carryOver = 1;
                      } else {
                          carryOver = 0;
                      }
                  }
              }

              if (carryOver > 0){
                  sum = Integer.toString(carryOver) + sum;
              }
              //   sign = 1 (negative), sign = 0 (positive)
              if (this.sign == 1){
                  return new BigInt("-" + sum);
              } else {
                  return new BigInt(sum);
              }
          }
        //   throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
      }


      /**
       *  Method to subtract a BigInt passed in as an argument to this BigInt
       *  @param  otherInt other BigInt to subtract from this BigInt
       *  @return BigInt that's the difference of this BigInt and the one passed in
       */
       public BigInt subtract(BigInt otherInt) {
           String difference = "";

           byte compare = (byte) this.compareTo(otherInt);

           BigInt input2 = new BigInt(this.toString());
           BigInt other2 = new BigInt(otherInt.toString());

        //    MAKING SURE THE SIGNS GO IN THE RIGHT PLACE

        //    if both are negative and input is bigger than other, final answer is negative
        //    (-largerA - -smallerB)  ex) (-5 - -3) => 5 - 3 = "-" + 2 = -2
           if ((this.sign == 1) && (otherInt.sign == 1) && (this.compareTo(otherInt) == 1)){
               input2.sign = 0;
               other2.sign = 0;
               difference = ("-" + input2.subtract(other2));
               return new BigInt(difference);
            //    return difference;
           }

        //    if both are negative and otherint is bigger than the input, final answer is positive (signs cancel)
        //    (-smallerA - -largerB)  ex) (-3 - -5) => 5 - 3 = 2
           if ((this.sign == 1) && (otherInt.sign == 1) && (this.compareTo(otherInt) == -1)){
               input2.sign = 0;
               other2.sign = 0;
            //    subtract other by input because signs cancel
               return other2.subtract(input2);
           }

        //    other is negative, signs cancel out making it addition (a - -b)
        //    ex) 5 - -3 => 5 + 3 = 8
           if ((this.sign == 0) && (otherInt.sign == 1)){
               other2.sign = 0;
               return this.add(other2);
           }

        //    input is negative (-a - b)
        //    ex) -5 - 3 => 5 + 3 = "-" + 8 = -8
           if((this.sign == 1) && (otherInt.sign == 0)){
               input2.sign = 0;
               return this.add(otherInt);
           }

           //   (compareTo) if input is greater than otherint, compare = 1
           if (compare >= 0){
            //    loop through input in byte array
               for (int i = 0; i < this.byteArray.length; i++){
                   if (i >= otherInt.byteArray.length){
                       difference = Integer.toString(this.byteArray[i]) + difference;
                   } else {
                       if ((this.byteArray[i] - otherInt.byteArray[i]) < 0){
                           this.byteArray[i-1] -= 10;
                           this.byteArray[i] += 10;
                           difference = Integer.toString((this.byteArray[i] - otherInt.byteArray[i])) + difference;
                       } else {
                           difference = Integer.toString((this.byteArray[i] - otherInt.byteArray[i])) + difference;
                       }
                   }
               }
           } else {
               for (int i = 0; i < otherInt.byteArray.length; i++){
                   if (i >= this.byteArray.length){
                       difference = Integer.toString(otherInt.byteArray[i]) + difference;
                   } else {
                       if ((otherInt.byteArray[i] - this.byteArray[i]) < 0){
                           otherInt.byteArray[i-1] -= 10;
                           otherInt.byteArray[i] += 10;
                           difference = Integer.toString((otherInt.byteArray[i] - this.byteArray[i])) + difference;
                       } else {
                           difference = Integer.toString((otherInt.byteArray[i] - this.byteArray[i])) + difference;
                       }
                   }
               }
               difference = ("-" + difference);
           }

           return new BigInt(difference);

        //    throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
       }


    //    /**
    //     *  Method to multiply a BigInt passed in as an argument to this BigInt
    //     *  @param  otherInt other BigInt to multiply by this BigInt
    //     *  @return BigInt that's the product of this BigInt and the one passed in
    //     */
    //     public BigInt multiply(BigInt otherInt) {
    //         // (Optional) your code here
    //         throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    //     }


    //     /**
    //      *  Method to divide a BigInt passed in as an argument from this BigInt
    //      *  @param  otherInt other BigInt to divide into this BigInt
    //      *  @return BigInt that's the ratio of this BigInt and the one passed in
    //      */
    //      public BigInt divide(BigInt otherInt) {
    //          // (Optional) your code here
    //          throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    //      }


    //      /**
    //       *  Method to find the remainder after dividing by a BigInt passed in
    //       *  @param  otherInt other BigInt to divide into this BigInt to compute the remainder
    //       *  @return BigInt that's the remainder after dividing the two BigInts
    //       */
    //       public BigInt remainder(BigInt otherInt) {
    //           // (Optional) your code here
    //           throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
    //       }


          /**
           *  Method to compare this BigInt to another BigInt passed in
           *  @param  otherInt other BigInt to compare to
           *  @return 1 if this BigInt is larger, 0 if equal, -1 if it's smaller
           */
           public int compareTo(BigInt otherInt) {
                //    if value is negative and otherInt is positive, otherInt (smaller)
               if ((1 == sign) && (otherInt.sign == 0)){
                   return -1;
               }
            
                //    if value is positive and otherInt is negative, otherInt (larger)
               if ((0 == sign) && (otherInt.sign == 1)){
                   return 1;
               }

               //    if value and other int are both positive or negative (equal)
               if (sign == otherInt.sign){
                   // input smaller than otherInt(smaller), input larger than otherInt(larger), equal
                   if (inputVal.length() < otherInt.inputVal.length()){
                       return -1;
                   } else if (inputVal.length() > otherInt.inputVal.length()){
                       return 1;
                   } else {
                       for(int i = 0; i<inputVal.length(); i++){
                        char first = Character.valueOf(inputVal.charAt(i));
                        char second = Character.valueOf(otherInt.inputVal.charAt(i));
                        if (first > second){
                            return 1;
                        } else if (first < second){
                            return (-1);
                        }  
                       }
                    //    char check = 'c';
                    //    for (int i = 0; i<inputVal.length(); i++){
                    //        char first = Character.valueOf(inputVal.charAt(i));
                    //        char second = Character.valueOf(otherInt.inputVal.charAt(i));
                    //        System.out.println(first);

                    //     //    input bigger than other, input smaller, equal
                    //        if (first > second){
                    //            check = 'a';
                    //        } else if (first < second){
                    //            check = 'b';
                    //        }
                    //    }
                    //    if (check == 'a'){
                    //        return 1;
                    //    } else if (check == 'b'){
                    //        return -1;
                    //    } else if (check == 'c'){
                    //        return 0;
                    //    }
                   }
                   return 0;
               }
               return -10;
           }


           /**
            *  Method to check if this BigInt equals another BigInt passed in
            *  @param  otherInt other BigInt to compare to
            *  @return true if they're equal, false otherwise
            */
            public boolean equals(BigInt otherInt) {
                
                return (inputVal.equals(otherInt.toString()));
            }


           /**
            *  Method to return the string representation of this BigInt
            *  @return String representation
            */
            public String toString() {
                String output = "";
                for (int i = 0; i < charArray.length; i++){
                    output = output.concat(Character.toString(charArray[i]));
                }
                output = new String(new StringBuffer(output).reverse());
                if (sign == 1){
                    output = "-" + output;
                }

                return output;
            }


}
