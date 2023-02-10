public class Tribonacci{
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        if(n<0){System.out.println("only non negative inputs");}else{
        System.out.println(getTribonacci(n));
        System.out.println(iterativeTribonacci(n));}

        boolean a = false;
        boolean b = true;
        boolean c = true;
        System.out.println(majority(a, b, c));

    }

    public static int getTribonacci(int n){
        if(n == 0){return 0;}
        if(n == 1 || n == 2){return 1;}
        return getTribonacci(n-3) + getTribonacci(n-2) + getTribonacci(n-1);
    }

    public static int iterativeTribonacci(int n){
        if(n <=  0){return 0;}
        if(n == 1 || n == 2){return 1;}

        int prevprev = 0;
        int prev = 1;
        int tri = 1;
        for(int i = 2; i < n; i++){
            int temp = tri;
            tri = tri + prev + prevprev;
            prevprev = prev;
            prev = temp;
        }
    
        return tri;
    }

    public static boolean majority(boolean first, boolean second, boolean third){
        return (first && second) || third;
    }
}