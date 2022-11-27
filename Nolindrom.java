public class Nolindrom {
    
    public static long inverse(long input) {

        String inputstring = String.valueOf(input);
        int l = inputstring.length();
        long aux;
        long output = 0;
        long[] outputarray;
        outputarray = new long[l];

        for(int i = 0; i < l; i++){
            aux = input % 10;
            input = (long) input / 10;
            outputarray[i] = aux * (long) Math.pow(10, (l-1 - i));

        }

        for(int i = 0; i < outputarray.length; i++){
            output = output + outputarray[i];
        }

        return output;
    }

    public static boolean checkifpalindrom(long input) {

        long org = input;
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
        }
        return output;
    }
    
    public static void main (String[] args) {
        int upperlimit = Integer.parseInt(args[0]);
        long zahl;
        boolean ifnotterminate[];
        ifnotterminate = new boolean[upperlimit+1];

        for(int i = 1; i <= upperlimit; i++){

            zahl = (long) i;
            long erg = zahl;
            long r = 0;
            long count = 0;

        
            do {
                if (Long.MAX_VALUE - erg < inverse(erg)){
                    ifnotterminate[i] = true;
                    break;
                } 
                else { 
                    zahl = erg;
                    r = inverse(erg);
                    erg = erg + r;
                    count++;
                }
            }
            while(checkifpalindrom(erg) == false);
            // System.out.println("Zahl: " + i + " Palindrom: " + erg + " Iterationen: " + count);
        
        }

        int numberofnumbers = 0;
        // System.out.println("these numbers: ");
        for(int i = 1; i <= upperlimit; i++){
            if(ifnotterminate[i] == true){
                System.out.println(i);
                numberofnumbers++;
            }
        }
        // System.out.println("there are: " + numberofnumbers + " such numbers");
    }
} 