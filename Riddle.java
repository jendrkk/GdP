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
     * Function that mirrors an array if the first digit of this array is larger than the last digit of this array.
     * 
     */

    public static int[] mirror(int[] input){
        int[] output = new int[input.length];
        if(input[0] > input[input.length-1]){
            for(int i = 0; i < input.length; i++){
                output[i] = input[input.length-i-1];   
            }
            return output;    
        }
        return input;
    }

    /*
     * Classic mathematical function min{.,.} 
     */

    public static int min(int a, int b){
        if(a > b){
            return b;
        }
        return a;
    }

    /*
     * Recursive defined function, which is solving the Riddle. Input of this function is an array and its length divided by 2 (when the function is called for the first time in the section main()). 
     * The argument "digit" (although it isn't always a digit) is also defining the integer, that will be inserted to the array, when the function will be called for the first time in main(), and
     * also in the case, when function calls itself. Output of this function is into void, in order to print (with printArray) the solution directly after the recursion will be finished (n = 0) for some array, i.e.
     * directly, when the solution will be found. Using "void" we don't have to have "return;" at the end.
     */

    public static void solve(int[] input, int digit){

        /*
         * The following if-statement is responsible for "choosing" the type of output according to n (input.length/2), as desired in the problem definition.
         * The next if-statements (in both cases, both output types) are checking if the recursion has been finished (n = 0), i.e. Base Case has been achieved.
         * Every time the base case is achieved, a solution has been found and 1 is added to the count (global) variable, i.e. number of found solutions (which are not mirrored -> why? later explained).
         * Additionally, when n < 10, every solution will be printed, or mirrored and printed (when mirrored? -> defined in comments to mirror()).
         */

        if(input.length/2 < 10){
            if(digit == 0){
                printArray(mirror(input));
                count++;
                return;
            }
        } else {
            if(digit == 0){
                count++;
                return;
            }
        }

        /*
         * The following for loop is bounded by the inserted digit multiplied by (n-1) or n, whatever is smaller (thanks to the min() function). The case of n is clear. The other case: 
         * This allows us to shrink the number of solution by 50%. Such a boundary eliminates a possibility that a mirrored array can be generated in some other case (when i in for-loop has a larger value).
         * Of course the generated solutions (with such constraint) don't have to start with a smaller number and that is the reason why mirror() is applied to every generated array, i.e. solution.
         * In other words:
         * A pair of 2 digits (especially smaller like 1 or 2 in longer arrays) is inserted only if the first one is in the left "(roughly) half" of an array (depending on the digit of course).
         * If it is inserted in the right "half", these cases can be mirrored to the left "half" ones. I'm sorry if this explanation isn't explanatory at all :(.
         * If-Statement in the for-loop checks if a positions i and i+1+digit [number which will be inserted] in the array are free, i.e. there are zeros there, and if the index i+1+digit isn't out of bounds (thats why it's in the middle of these "ands" &&).
         * Then follows the reduction step, and the function solve() is getting called with array after insertion of a number "digit" and a number "digit-1". so that it "converges".
         * Then the positions, where the insertion has been proceeded, are again set free, i.e. their values are set to 0. This allows to produce a new (different) solution in the next iteration of the for-loop, i.e. for an another i.
         */

        for(int i = 0; i < min(digit*(input.length/2-1),input.length); i++){
            if(input[i] == 0 && i+digit+1 < input.length && input[i+digit+1] == 0){
                input[i] = digit;
                input[i+digit+1] = digit;

                solve(input, digit-1);

                input[i] = 0;
                input[i+digit+1] = 0;
            }
        }

    }

    /*
     * In main section there isn't much to explain.
     * There are two "error notifications": if there are no command line parameters, and if the parameters are exceeding the boundaries given in the task.
     * If everything is ok with parameters, there will be generated a starting array (of course with only zeros, so it's empty assuming that 0 means that a position is "free") of length 2*n and 
     * the solve() function is called for the first time with this array and the half of its length, i.e. n, as arguments.
     * Then there is an if-else-statement responsible for printing the number of solutions in a fancy way.
     */

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        if(args.length == 0){ 
            System.out.println("Bitte eine Zahl als Parameter angeben.");
        } else {
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
}