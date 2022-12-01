import java.util.Arrays;

public class Sieb {
    public static boolean[] primes(int input){
        int[] remainders;
        remainders = new int[input];
        boolean[] aux;
        aux = new boolean[input];
        boolean[] ifprime;
        ifprime = new boolean[input-1];
        int count = 0;
    
        for(int i = 2; i <= input; i++){
            for(int j = 1; j <= input; j++){
                remainders[j-1] = i % j;
            }
            for(int k = 1; k <= input; k++){
                if(k != i){
                    if(remainders[k-1] != 0){
                        aux[k-1] = false;
                    } else {
                        aux[k-1] = true;
                    }
                } else {
                    if(remainders[k-1] == 0){
                        aux[k-1] = true;
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
                ifprime[i-2] = true;
            } else {
                ifprime[i-2] = false;
            }
        }
        return ifprime;
    }
    public static void main (String[] args){
        int n = Integer.parseInt(args[0]);
        int limit = 9;
        int count = 0;
        int i = 0;
        
        do{
            limit = limit + 10*i;
            for(int j = 0; j < limit-1; j++){
                if(primes(limit)[j]){
                    count++;
                 }
            }
            if(count >= n){
                break;
             } else {
                count = 0;
             }
            i++;
        }while(count <= n);

        /*
        System.out.println(count);
        System.out.println("\n" + "Primes:");
        */
        
        count = 0;

        for(int k = 2; k <= limit; k++){
            if(primes(limit)[k-2] && count < n){
                System.out.println(k);
                count++;
            }
        }
        
        /*
        System.out.println("\n");
        System.out.println(limit);
        System.out.println("\n");
        System.out.println(count);
        */
    }
}