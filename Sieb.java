public class Sieb {

/*
 * We are defining the function "primes". Its output is an array with primes and zeros to a given limit (input). When the index of this array is prime, 
 * there's this prime number in that place, when the index isn't a prime number, then there is a "0" there. Such a definition of "primes" save time, 
 * since we are getting ready-to-print primes as the output.
 */

    public static int[] primes(int input){
        int[] primes = new int[input-1];
        int count = 0;

        /*
         * For every natural number we are using an auxiliary array "aux", which holds true/false values:
         * true: if the reminder from i % j is 0
         * false: in any other case; we are using the fact that every boolean array is by default filled with false values. 
         */
    
        for(int i = 2; i <= input; i++){                            // Loop that goes through every natural number (2<=i<=limit); candidates for prime numbers
            boolean[] aux  = new boolean[i];
            count = 0;                          
            for(int j = 1; j <= i; j++){                            // Loop that goes through every natural number (2<=j<=i); modulo divisors
                if(j != i){                           
                    if((i % j) == 0){
                        aux[j-1] = true;
                    }
                } else {
                    if((i % j) == 0){
                        aux[j-1] = true;
                    }
                }
            }
            for(int k = 1; k <= aux.length; k++){                   // This loop sums up the true values in the aux array for candidate i
                if(aux[k-1]){
                    count++;
                }                                       
            }
            if(count == 2){                                         // If this sum is exactly equal 2, then we have found a prime number.
                primes[i-2] = i;                                    // At the index i of the output array we're assigning i if prime and 
            } else {
                primes[i-2] = 0;                                    // zero if not prime.
            }
        }
        return primes;                                              // We're defining output for primes function as the array "primes".
    }

    public static void main (String[] args){
        int n = Integer.parseInt(args[0]);          
        int limit = 9;
        int found = 0;
        int i = 0;
        
/*
 * Do-while loop calls the function primes for a given (current) limit to which primes will be searched. If the number of found primes is greater equal the
 * number of primes to be found, then the loop breaks, if not: the limit is raised by a factor of 10, 
 * [for the i iteration if the do-while loop we have limit = 9+10i]
 */

        do{
            limit = limit + 10*i;
            int[] primes = new int[limit-1];
            primes = primes(limit);
            for(int j = 0; j < limit-1; j++){
                if(primes[j] != 0){
                    found++;
                 }
            }
            if(found >= n){
                break;
             } else {
                found = 0;
             }
            i++;
        }while(true);

/*
 * Since the limit has been "defined" by the primes search in the do-while loop, we are calling the function primes for the last time with this limit
 * as an argument (calling "primes" isn't really time efficient), so that we can print our primes with a simple for-loop with an if-statement, which is
 * searching for nonzero indices of primes array and takes care of a number of printed primes (the "primes" function doesn't allow us to have control over 
 * the number of primes in the array).
 */

        int[] primes = new int[limit-1];
        primes = primes(limit);                         
        int count = 0;                                  
    
        for(int k = 2; k <= limit; k++){                    
            if(primes[k-2] !=0 && count < n){
                System.out.println(k);
                count++;
            }
        }
    }
}