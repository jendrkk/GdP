import gdp.stdlib.*;

public class GameOfLife {

/////////////////////////// FUNCTIONS ///////////////////////////

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

    public static void grid(boolean input, int[] size){
        if(input){
            for(int i = 0; i <= size[0]; i++){
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(size[0] , i, 0, i);
            }
            for(int j = 0; j <= size[1]; j++){
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(j , size[1], j, 0);
            }
        }
        return;
    }

    public static void print(int[][] input, int[] size){
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
                if(input[i][j] == 1){
                    StdDraw.setPenColor(StdDraw.GREEN);
                    StdDraw.filledSquare(0.5 + j, 0.5 + i, 0.5);
                } else {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledSquare(0.5 + j, 0.5 + i, 0.5);
                }
            }
        }
    }

/////////////////////////// MAIN ///////////////////////////
    
    public static void main(String[] args){
        /* 
         * In this part of code is defined how the start configuration is loaded. It is possible because of the StdIn.readInt() function.
         * Apart from that we define the structers (matrix, size) which are required to implementing the code.
        */
        int row = StdIn.readInt();
        int col = StdIn.readInt();
        int alive = StdIn.readInt();
        int[][] coordinates = new int[alive][2];
        int[][] matrix = new int[row][col];
        int[] size = new int[2];
        size[0] = row; size[1] = col;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrix[i][j] = 0;
            }
        }
        int count = 0;
        while(!StdIn.isEmpty()){
            coordinates[count][0] = StdIn.readInt();
            coordinates[count][1] = StdIn.readInt();
            count++;
        }
        for(int i = 0; i < alive; i++){
            matrix[coordinates[i][1]][coordinates[i][0]] = 1;
        }

        /* 
         * In this part of code are defined commands, that are requierd to print the gird and display the starting configuration.
         * The while-loop is responible for applying the rules of "Game of Life" and displaying every state for 200ms.
        */
      
        StdDraw.setXscale(0, +size[0]);
        StdDraw.setYscale(0, +size[1]);
        print(matrix, size);
        grid(true, size);
        StdDraw.show(200);
        
        while(true){
            matrix = nextstate(matrix);
            print(matrix, size);
            grid(true, size);
            StdDraw.show(200);
        }
    }
}
