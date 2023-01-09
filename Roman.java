public class Roman {

    /*
     * roman() is a recursive defined function with a string as an output.
     * The final sting is produced as a (concatenated) sequence of strings generated as the reduction of the number, which is to be translated to roman notation, continues. Eventually the reduction reaches
     * the base case, i.e. number = 0, which isn't reducible, and in roman notation is an empty space.
     * The sequence of if-else-statements is responsible for considering every case which can occur during the translation, including those when a character should be before the other (main) character (CM for 900 or IX for 9).
     */

    public static String roman(int n) {
        String output;

        if(n >= 1000){
            output = "M" + roman(n-1000);
            return output;
        } else if(n >= 900){
            output = "CM" + roman(n-900);
            return output;
        } else if(n >= 500) {
            output = "D" + roman(n-500);
            return output;
        } else if(n >= 400){
            output = "CD" + roman(n-400);
            return output;
        } else if(n >= 100){
            output = "C" + roman(n-100);    
            return output;
        } else if(n >= 90){
            output = "XC" + roman(n-90);
            return output;
        } else if(n >= 50){
            output = "L" + roman(n-50);
            return output;
        } else if(n >= 40){
            output = "XL" + roman(n-40);
            return output;
        } else if(n >= 10){
            output = "X" + roman(n-10);
            return output;
        } else if(n >= 9){
            output = "IX" + roman(n-9);
            return output;
        } else if(n >= 5){
            output = "V" + roman(n-5);
            return output;
        } else if(n >= 4){
            output = "IV" + roman(n-4);
            return output;
        } else if(n >= 1){
            output = "I" + roman(n-1);
            return output;
        } else if (n == 0){return output = "";}
        
        return output = "";
    }

    /*
     * In the section main() there are 2 "error notification". The first one is activated, when there isn't given any command line argument and 
     * the other one, when the number N exceeds the assumed boundaries from the task formulation.
     * If everything is okk with the parameters, the output of roman() (i.e. String) for N as the argument is printed.
     */

    public static void main(String[] args) {

        if(args.length == 0){ 
            System.out.println("Bitte eine Zahl als Parameter angeben.");
        } else {
            int N = Integer.parseInt(args[0]);

            if(N <= 0 || N > 5000){
                System.out.println("Die Zahl muss zwischen 1 und 5000 liegen.");
            } else {
                assert(1<=N && N<=5000);
                System.out.println(roman(N));
            }
        }
    }
}

