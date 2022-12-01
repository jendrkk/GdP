import java.util.Arrays;
import java.util.function.IntPredicate;

//import gdp.stdlib.*;

public class GameOfLife {
     
    public static int[][] neighbourhood(int[][] input){
        int[][] output = new int[input.length][input[0].length];

        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                if(i == 0 && j == 0){
                    output[i][j] = input[i+1][j+1] + input[i][j+1] + input[i+1][j] + input[input.length-1][j] + input[input.length-2][j] + input[i][input[0].length-1] + input[i][input[0].length-2] + input[input.length-1][input[0].length-1];
                } else if(i == 0 && j != 0 && j != input.length-1){
                    output[i][j] = input[i][j+1] + input[i][j-1] + input[i+1][j+1] + input[i+1][j] + input[i+1][j-1] + input[input.length-1][j] +input[input.length-1][j-1] + input[input.length-1][j+1];
                } else if(i == 0 && j == input[0].length-1){
                    output[i][j] = input[i][j-1] + input[i+1][j]+ input[i+1][j-1] + input[input.length-1][j] + input[input.length-1][j-1] + input[input.length-1][0] + input[i][0] + input[i+1][0];
                } else if(j == input[0].length-1 && i != 0 && i != input.length-1){
                    output[i][j] = input[i-1][j] + input[i+1][j] + input[i][j-1] + input[i-1][j-1] + input[i+1][j-1] + input[i][0] + input[i-1][0] + input[i+1][0];
                } else if(i == input.length-1 && j == input[0].length-1){
                    output[i][j] = input[i-1][j] + input[i-1][j] + input[i-1][j-1] + input[i][0] + input[i-1][0] + input[0][0] + input[0][j] + input[0][j-1];
                } else if(i == input.length-1 && j != input[0].length-1 && j != 0){
                    output[i][j] = input[i][j-1] + input [i][j+1] + input[i-1][j-1] + input[i-1][j] + input[i-1][j+1] + input[0][j-1] + input[0][j] + input[0][j+1];
                } else if(i == input.length-1 && j == 0){
                    output[i][j] = input[i-1][j] + input[i][j+1] + input[i-1][j+1] + input[i][input[0].length-1] + input[i-1][input[0].length-1] + input[0][input[0].length-1] + input[0][j] + input[0][j+1];
                } else if(j == 0 && i != 0 && i != input.length-1){
                    output[i][j] = input[i-1][j] + input[i+1][j] + input[i][j+1] + input[i-1][j+1] + input[i+1][j+1] + input[i-1][input[0].length-1] + input[i][input[0].length-1] + input[i+1][input[0].length-1];
                } else {
                    output[i][j] = input[i-1][j-1] + input[i-1][j] + input[i-1][j+1] + input[i][j+1] + input[i+1][j+1] + input[i+1][j] + input[i+1][j-1] + input[i][j-1];
                }
            }
        }
        return output;
    }

    public static int[][] translate(boolean[][] input){
        int[][] output = new int[input.length][input[0].length];

        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                if(input[i][j]){
                    output[i][j] = 1;
                } else {
                    output[i][j] = 0;
                }
            }
        }
        return output;
    }

    public static boolean[][] reversetranslate(int[][] input){
        boolean[][] output = new boolean[input.length][input[0].length];

        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                if(input[i][j] == 1){
                    output[i][j] = true;
                } else {
                    output[i][j] = false;
                }
            }
        }
        return output;
    }

    public static int[][] nextstate(int[][] input){
        int[][] output = new int[input.length][input[0].length];

        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                if(input[i][j] == 1 && (neighbourhood(input)[i][j] == 2 || neighbourhood(input)[i][j] == 3)){
                    output[i][j] = 1;
                } else if(input[i][j] == 0 && neighbourhood(input)[i][j] == 3){
                    output[i][j] = 1;
                } else {
                    output[i][j] = 0;
                }
            }
        }
        return output;
    }

    /*
    public static boolean[][] read() { 
        int N = StdIn.readInt(); 
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++)
            if (StdIn.readInt() != 0) a[i][j] = true;
        }
        return a; 
    }
    */
    
    
    public static void main(String[] args){
        int rows = 10;
        int cols = 10;
        
        int[][] matrix = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                matrix[i][j] = 0;
            }
        }

        for(int k = 0; k <= 10; k++){
            double index1 = k * Math.random();
            int p = (int) index1;
            double index2 = k * Math.random();
            int q = (int) index2;

            matrix[p][q] = 1;
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("\n");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(neighbourhood(matrix)[i][j] + "  ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("\n");
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(nextstate(matrix)[i][j] + "  ");
            }
            System.out.println();
            System.out.println();
        }
    }
}
