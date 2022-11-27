public class Nolindrom {
    
/*
 * The following function "inverse" inverts a given "input" number, so that its output is the "input" number read backwards.
 * This function has been predefined in ordert to keep the code clear and readable.
 */
    public static long inverse(long input) {

        String inputstring = String.valueOf(input);         // Input is converted to String
        int l = inputstring.length();                       // l is a variable holding the number of characters in that String, ergo: length of input number
        long aux;                                           // aux: auxiliary variable
        long output = 0;           
        long[] outputarray;                                 
        outputarray = new long[l];                          // outputarray: array that holds the decimal components of output (inverted) number

        for(int i = 0; i < l; i++){
            aux = input % 10;
            input = (long) input / 10;
            outputarray[i] = aux * (long) Math.pow(10, (l-1 - i));

        }                                                   // Loop that inverts the input number and creates the decimal componetnts (i.e. for 124: 100 20 4)

        for(int i = 0; i < outputarray.length; i++){
            output = output + outputarray[i];
        }                                                   // Loop that sums the decimal components in order to compose the output number

        return output;                                     
    }

/*
 * The following function "checkifpalindrom" checks a given "input" number and assigns "true" if it is a palindrom
 * This function has been predefined in ordert to keep the code clear and readable.
 * The consturction of this function is similar to the "inverse" function.
 */

    public static boolean checkifpalindrom(long input) {

        long org = input;                                   // org: original input number
        String inputstring = String.valueOf(input);
        int l = inputstring.length();
        long aux;
        long invers = 0;
        boolean output;
        long[] outputarray;
        outputarray = new long[l];

        for(int i = 0; i < l; i++){
            aux = input % 10;
            input = (long) input / 10;
            outputarray[i] = aux * (long) Math.pow(10, (l-1 - i));
        }

        for(int i = 0; i < outputarray.length; i++){
            invers = invers + outputarray[i];
        }

        if(org - invers == 0){
            output = true;
        } else {
            output = false;
        }                                                   // If-statement that assigns "true" to boolean variable "output" if the difference between the original 
                                                            // number and the inverted number (input read backwards) is zero (ergo: these numbers are same -> palindrom)
        return output;
    }
    
    public static void main (String[] args) {
  
        if (args.length == 0) {
            System.out.println("Bitte geben Sie die Obergrenze als Parameter an.");
            return;
        }                                                                                   // Enforce proper usage

        int upperlimit = Integer.parseInt(args[0]);                                         // upperlimit: command line argument
        long zahl;                                          
        boolean ifnotterminate[];
        ifnotterminate = new boolean[upperlimit+1];                                         // ifnotterminate: boolean array that stores the ture/false
                                                                                            // if the algorithm terminates for a given number

        /*
         * Loop that goes through each natural number until it reaches the (given) upper limit
         */

        for(int i = 1; i <= upperlimit; i++){

            zahl = (long) i;
            long erg = zahl;
            long r = 0;

            /*
             * Do-while loop that is searching for a palindrom for every "i" number, it terminates if it finds such palindrom number or if the numbers
             * which are required to find such palindrom exceed the maximal integer that can be stored as "long"; if-statement that is responsible for checking 
             * this case is starred (*)
             */

            do {
                if (Long.MAX_VALUE - erg < inverse(erg)){               // (*)
                    ifnotterminate[i] = true;
                    break;
                } 
                else { 
                    zahl = erg;
                    r = inverse(erg);
                    erg = erg + r;
                }
            }
            while(checkifpalindrom(erg) == false);
        }  

        /*
         * Loop that prints out every number for which the algorithm not terminates
         */

        for(int i = 1; i <= upperlimit; i++){
            if(ifnotterminate[i] == true){
                System.out.println(i);
            }
        }
    }
} 