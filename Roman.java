public class Roman {

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

