import java.util.Arrays;

public class Riddle {

    /*
     * Declaration of a global variable, which is responsible for counting the printed arrays.
     */

    public static int count;

    /*
     * Classic function to print arrays.
     */

    public static void printArray(int[] input){
		for (int i = 0; i < input.length; i++){
			System.out.print(input[i]);
		}
        System.out.println();
    }

    /*
     * Function that checks if 2 arrays of the same length are a mirrored versions of each other, i.e. there are the same according to assumptions of this exercise.
     */

    public static boolean ifMirrored(int[] input1, int[] input2){
        boolean output = false;
        int tempCount = 0;
        
        for(int i = 0; i < input1.length; i++){
            if(input1[i] == input2[input2.length - i - 1]){
                tempCount++;
            }
        }

        if(tempCount == input1.length){
            return true;
        }

        return output;
    }

    public static void solve(int[] input, int digit){
        if(input.length/2 < 10){
            if(digit == 0){
                printArray(input);
                count++;
                return;
            }
        } else {
            if(digit == 0){
                count++;
                return;
            }
        }

        for(int i = 0; i < input.length; i++){
            if(input[i] == 0 && i+digit+1 < input.length && input[i+digit+1] == 0){
                input[i] = digit;
                input[i+digit+1] = digit;

                solve(input, digit-1);

                input[i] = 0;
                input[i+digit+1] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        if(n > 0 && n <= 15){
            int[] input = new int[2*n];

            solve(input, n);
            
            if(count == 0){
                System.out.println("keine Loesung");
            } else if(count == 1){
                System.out.println("eine Loesung");
            } else {
                System.out.println(count + " Loesungen");
            }

        } else {
            System.out.println("Die Zahl muss zwischen groeßer als 0 und kleiner gleich 15 sein.");
        }
    }
}