import gdp.stdlib.*;

public class GameOfLife {

/////////////////////////// FUNCTIONS ///////////////////////////

/*
 * The function "neighbourhood" is counting the number of alive neighbours of a give cell. The grid is represented by a matrix of a size equal to the size
 * imported form a given starting configuration (the argument size). The input matrix of this function is a matrix which is also imported from this configuration,
 * with given living cells (any other cell is dead). For the purpose of finding neighbours, there has been defined a (unreadable but hopefully correct) series
 * of if-else-statements which are examining every possible case (4 corners, 4 edges excluding corners and the "interior", so 9 cases altogether). In the output
 * matrix there will be assigned numbers of neighbours of a spot ((i,j)-pair) at each such place, so for the whole matrix.
 */

    public static int[][] neighbourhood(int[][] input, int[] size){
        int[][] output = new int[size[0]][size[1]];

        for(int i = 0; i < size[0]; i++){
            for(int j = 0; j < size[1]; j++){
                if(i == 0 && j == 0){
                    output[i][j] = input[i+1][j+1] + input[i][j+1] + input[i+1][j] + input[size[0]-1][j] + input[size[0]-1][j+1] + input[i][size[1]-1] + input[i+1][size[1]-1] + input[size[0]-1][size[1]-1];
                } else if((i == 0 && j != 0) && j != (size[1]-1)){
                    output[i][j] = input[i][j+1] + input[i][j-1] + input[i+1][j+1] + input[i+1][j] + input[i+1][j-1] + input[size[0]-1][j] +input[size[0]-1][j-1] + input[size[0]-1][j+1];
                } else if(i == 0 && j == (size[1]-1)){
                    output[i][j] = input[i][j-1] + input[i+1][j]+ input[i+1][j-1] + input[size[0]-1][j] + input[size[0]-1][j-1] + input[size[0]-1][0] + input[i][0] + input[i+1][0];
                } else if((j == (size[1]-1) && i != 0) && i != (size[0]-1)){
                    output[i][j] = input[i-1][j] + input[i+1][j] + input[i][j-1] + input[i-1][j-1] + input[i+1][j-1] + input[i][0] + input[i-1][0] + input[i+1][0];
                } else if(i == (size[0]-1) && j == (size[1]-1)){
                    output[i][j] = input[i-1][j] + input[i][j-1] + input[i-1][j-1] + input[i][0] + input[i-1][0] + input[0][0] + input[0][j] + input[0][j-1];
                } else if((i == (size[0]-1) && j != (size[1]-1)) && j != 0){
                    output[i][j] = input[i][j-1] + input [i][j+1] + input[i-1][j-1] + input[i-1][j] + input[i-1][j+1] + input[0][j-1] + input[0][j] + input[0][j+1];
                } else if(i == (size[0]-1) && j == 0){
                    output[i][j] = input[i-1][j] + input[i][j+1] + input[i-1][j+1] + input[i][size[1]-1] + input[i-1][size[1]-1] + input[0][size[1]-1] + input[0][j] + input[0][j+1];
                } else if((j == 0 && i != 0) && i != (size[0]-1)){
                    output[i][j] = input[i-1][j] + input[i+1][j] + input[i][j+1] + input[i-1][j+1] + input[i+1][j+1] + input[i-1][size[1]-1] + input[i][size[1]-1] + input[i+1][size[1]-1];
                } else {
                    output[i][j] = input[i-1][j-1] + input[i-1][j] + input[i-1][j+1] + input[i][j+1] + input[i+1][j+1] + input[i+1][j] + input[i+1][j-1] + input[i][j-1];
                }
            }
        }
        return output;
    }

/*
 * The function "nextstate" is calculating the next matrix (after applying the game rules) from a given input matrix. This function is also using the size
 * argument, since the neighbouthood function uses this argument and the "nextstate" function is the only place in the code where the "neighbourhood" function
 * is being called. The if-statements with rules of the game of life are in a double for-loop, so that every (i,j)-pair, so every place in the input matrix, is
 * considered. 
 */

    public static int[][] nextstate(int[][] input, int[] size){
        int[][] output = new int[size[0]][size[1]];

        for(int i = 0; i < size[0]; i++){
            for(int j = 0; j < size[1]; j++){
                if(input[i][j] == 1 && (neighbourhood(input, size)[i][j] == 2 || neighbourhood(input, size)[i][j] == 3)){
                    output[i][j] = 1;
                } else if(input[i][j] == 0 && neighbourhood(input, size)[i][j] == 3){
                    output[i][j] = 1;
                } else {
                    output[i][j] = 0;
                }
            }
        }
        return output;
    }

/*
 * The "gird" function is responsible for printing the blue grid, given its size. Here it's important to consider 2 cases: quadratic matrix and a (horizontally; 
 * see the starting configurations: we have only 30x40 matrix as a not quadratic matrix) rectangular matrix.
 */

    public static void grid(int[] size){
            if(size[0] == size[1]){
                for(int i = 0; i <= size[0]; i++){
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.line(size[0] , i, 0, i);
                }
                for(int j = 0; j <= size[1]; j++){
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.line(j, size[1], j, 0);
                }
            } else {
                for(int i = 0; i <= size[0]; i++){
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.line(size[1] , i, 0, i);
                }

                for(int j = 0; j <= size[1]; j++){
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.line(j, size[0], j, 0);
                }
            }
        return;
    }

/*
 * The "print" function is responsible for printing (or overprinting) the cells. This function is visualizing the input matrix. It prints a green square for a
 * living cell (there is 1 at (i,j) spot in the input matrix) or a white square for a dead cell (there is 0 at (i,j) spot in the input matrix).
 */

    public static void print(int[][] input, int[] size){
        for(int i = 0; i < size[0]; i++){
            for(int j = 0; j < size[1]; j++){
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
         * In the first part of code is defined how the start configuration is loaded. It is possible because of the StdIn.readInt() function. Firstly, we are 
         * loading 2 numbers that correspond to the size of a grid and the number of living cells (later useful for the for-loop). Secondly, we are defining the 
         * structures (matrix, size array) which are required to implement our code. Lastly, we are importing the coordinated of living cells and filling the
         * starting matrix.
        */

        int col = StdIn.readInt();
        int row = StdIn.readInt();
        int alive = StdIn.readInt();
        int[][] coordinates = new int[alive][2];
        int[][] matrix = new int[row][col];
        int[] size = new int[2];
        size[0] = row; size[1] = col;

        int count = 0;
        while(!StdIn.isEmpty()){
            coordinates[count][0] = StdIn.readInt();
            coordinates[count][1] = StdIn.readInt();
            count++;
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                matrix[i][j] = 0;
            }
        }
        for(int i = 0; i < alive; i++){
            matrix[coordinates[i][1]][coordinates[i][0]] = 1;
        }

        /* 
         * In the second part of code are defined commands, that are required to print the gird and display the starting configuration.
         * The while-loop is responsible for calling the "nextstate" function and printing the visualization of this matrix, which will be displayed each time
         * for 200ms, then the loop begins the next iteration, which we can see as an animation. (An appropriate do-while loop can be an alternative here.)
         * The while-loop will be repeated unless the displayed animation window will be closed by the user.
        */

        StdDraw.setCanvasSize(size[1]*25, size[0]*25);
        StdDraw.setXscale(0, +size[1]);
        StdDraw.setYscale(0, +size[0]);
        print(matrix, size);
        grid(size);
        StdDraw.show(200);
        
        while(true){
            matrix = nextstate(matrix, size);
            print(matrix, size);
            grid(size);
            StdDraw.show(200);
        }
    }
}
