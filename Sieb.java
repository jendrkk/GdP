public class Sieb {

/*
 * We are defining the function "primes". Its output is an array with primes and zeros to a given limit (input). When the index of this array is prime, 
 * there's this prime number in that place, when the index isn't a prime number, then there is a "0" there. Such a definition of "primes" save time, 
 * since we are getting ready-to-print primes as the output.
 */

    public static int[] primes(int input){
        boolean[] aux;
        aux = new boolean[input];
        int[] primes;
        primes = new int[input-1];
        int count = 0;

        /*
         * For every natural number we are using an auxiliary array "aux".
         */
    
        for(int i = 2; i <= input; i++){                            // Loop that goes through every natural number (2<=i<=limit); candidates for prime numbers
            for(int j = 1; j <= i; j++){
                if(j != i){
                    if((i % j) != 0){
                        aux[j-1] = false;
                    } else {
                        aux[j-1] = true;
                    }
                } else {
                    if((i % j) == 0){
                        aux[j-1] = true;
                    } else {
                        aux[j-1] = false;
                    }
                }
            }

            count = 0;

            for(int k = 1; k <= input; k++){
                if(aux[k-1]){
                    count++;
                }
            }
            if(count == 2){
                primes[i-2] = i;
            } else {
                primes[i-2] = 0;
            }
        }
        return primes;
    }

    public static void main (String[] args){
        int n = Integer.parseInt(args[0]);
        int limit = 9;
        int count = 0;
        int i = 0;
        
        do{
            limit = limit + 10*i;
            int[] currentprimes = new int[limit-1];
            currentprimes = primes(limit);
            for(int j = 0; j < limit-1; j++){
                if(currentprimes[j] != 0){
                    count++;
                 }
            }
            if(count >= n){
                break;
             } else {
                count = 0;
             }
            i++;
        }while(true);

        int[] currentprimes = new int[limit-1];
        currentprimes = primes(limit);
        count = 0;
    
        for(int k = 2; k <= limit; k++){
            if(currentprimes[k-2] !=0 && count < n){
                System.out.println(k);
                count++;
            }
        }
    }
}