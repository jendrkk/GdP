public class GGT {
    public static void main (String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        boolean acheck = (a > 0);
        boolean bcheck = (b > 0); 
        int m = a;
        int n = b;

        if (acheck == true && bcheck == true){
           while(m != n){
            if (m > n){
               m = m - n;
            } else {
                n = n - m; 
            }
           }
            System.out.println("ggT(" + a + ", " + b + ") = " + m);
        } else {
        System.out.println("nur positive ganze Zahlen als Argumente erlaubt");
    }
    }    
}