import gdp.stdlib.*;

public class MazeSolver {

	/*
	 * Firstly we're defining some global variables. Their values will be assigned when the function solve will be called for the first time.
	 * originalMaze: 2D-Array with original values of maze.
	 * deadEnds: boolean 2D-Array which holds information about dead ends in the maze.
	 * crossings: boolean 2D-Array which hold information about crossings in the maze ( *: positions[i][j] where there are two options to make a move which is allowed).
	 */

	public static int[][] originalMaze;

	public static boolean[][] deadEnds;

	public static boolean[][] crossings;

	/*
	 * Function ifDeadEnd returns true/false value for a given position in the maze with tells if this position is a dead end.
	 */

	public static boolean ifDeadEnd(int[][] maze, int row, int col){
		if(maze[row][col] == 1){
			if((row+1 < maze.length && maze[row+1][col] == 0) && (col-1 >= 0 && maze[row][col-1] == 0)){
			
				return true;
			} else if(col-1 < 0 && row+1 < maze.length && maze[row+1][col] == 0){
				
				return true;
			} else if(row+1 > maze.length && col >= 0 && maze[row][col-1] == 0){
				
				return true;
			}
		}

		return false;
	}

	/*
	 * Function ifCrossing returns true/false value for a given position in the maze with tells if there is a crossing (*) at this position.
	 */

	public static boolean ifCrossing(int[][] maze, int row, int col){
		
		if(maze[row][col] == 1){
			if(row+1 < maze.length && (maze[row+1][col] == 1 || maze[row+1][col] == 3) && col-1 >= 0 && (maze[row][col-1] == 1 || maze[row][col-1] == 3)){
				
				return true;
			}
		}
		
		return false;
	}

	/*
	 * Calls ifDeadEnd for every position in maze in order to return a boolean matrix with values for every pair of indices.
	 */

	public static boolean[][] deadEnds(int[][] maze){
		boolean[][] output = new boolean[maze.length][maze.length];

		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze.length; j++){
				output[i][j] = ifDeadEnd(maze, i, j);
			}
		}
		
		return output;
	}

	/*
	 * Calls ifCrossing for every position in maze in order to return a boolean matrix with values for every pair of indices.
	 */

	public static boolean[][] crossings(int[][] maze){
		boolean[][] output = new boolean[maze.length][maze.length];

		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze.length; j++){
				output[i][j] = ifCrossing(maze, i, j);
			}
		}
		
		return output;
	}

	/*
	 * Detailed comments to particular "chunks" of the function "solve" are in the function.
	 */

	public static boolean solve(int[][] maze, int row, int col) {
		
		/*
		 * First two if-statements are defined only for two unique cases.
		 * 1. if-Statement will be executed only if solve is being called for the first time and it is responsible for assigning values to global variables.
		 * 2. if-Statement will be executed only if recursion terminates, i.e. base case, so if and only if the maze is solvable.
		 */

		if(row == 0 && col == maze.length-1){
			originalMaze = maze;
			deadEnds = deadEnds(originalMaze);
			crossings = crossings(originalMaze);
		}

		if(row == maze.length-1 && col == 0){
			return true;
		}

		/*
		 * In the following sequence of if-else-statements there have been defined all cases, which can occur while computing the solution for a NxN-maze. Detailed comments have been written in the if-else-statement.
		 * As the first condition we're checking if the current position doesn't happen to be a dead end.
		 * As the second condition we're checking if the current position is a crossing. If it is, then the algorithm will always choose to "go to the left", i.e. reduce the variable "col" by 1.
		 * In the last case: Provided that there exist a permitted move (down/to the left), the function will favour a move down over a move to the left. 
		 * Every if-else-statement has a condition that prevents the index out of bounds error.
		 */

		if(deadEnds[row][col]){

			maze[row][col] = 1;																			// Set the position which is a dead end, to 1, i.e. "retreat" 

			int tempRow = row;																			// Some auxiliary variables.
			int tempCol = col;
			int antiLoop = 0;

			/*
			 * In the following do-while-loop we're "looking for" the nearest crossing, i.e. the crossing which led us to a dead end.
			 * In the first if-else-statement we check from which direction we came to the current position. As the loop continues (notabene: a crossing must be found within 2*matrix.length movements -> 
			 * why?: simple math: in the worst case we have to retreat "length" times to the right and "length" times up so 2*length) the indices of current position are reduced according to the moves that the algorithm has made (backtracking).
			 * If the "retreated" current position happens to be located at some point at a crossing, then the loop breaks. In the do-while-loop we're also marking (in the global variable deadEnds) the whole path with dead ends, i.e. assigning the true values 
			 * to every cell that has been a part of a path that led to a dead end. Thanks to this method, we can easily decide where to go at the nearest crossing which we have found (backtracked?).
			 */

			do{
				if(tempRow-1 >=0 && maze[tempRow-1][tempCol] == 2){
					tempRow = tempRow-1;
					maze[tempRow][tempCol] = 1;
					deadEnds[tempRow][tempCol] = true;
				} else if(tempCol+1 < maze.length && maze[tempRow][tempCol+1] == 2){
					tempCol = tempCol+1;
					maze[tempRow][tempCol] = 1;
					deadEnds[tempRow][tempCol] = true;
				}

				if(crossings[tempRow][tempCol]){
					break;
				}
				antiLoop++;

			}while(antiLoop < 2*maze.length);

			/*
			 * The move at the nearest crossing is done thanks to the following if-else-statement.
			 */

			if(tempRow+1 < maze.length && deadEnds[tempRow+1][tempCol] == false){

				maze[tempRow][tempCol] = 2;
				return solve(maze, tempRow+1, tempCol);

			} else if(tempCol-1 >= 0 && deadEnds[tempRow][tempCol-1] == false){

				maze[tempRow][tempCol] = 2;
				return solve(maze, tempRow, tempCol-1);

			}

		} else if(ifCrossing(maze, row, col)){

			maze[row][col] = 2;
			return solve(maze, row, col-1);

		} else{

			if(row+1 < maze.length && maze[row+1][col] != 0){
				maze[row][col] = 2;
				return solve(maze, row+1, col);
			} else if(col-1 >= 0 && maze[row][col-1] != 0){
				maze[row][col] = 2;
				return solve(maze, row, col-1);
			}

		}

		return false;
	}

	/*
	 * The following draw function is very similar to the draw function in GameOfLife code.
	 * The color of printed filled squares is chosen on the basis of values in the input matrix by a basic if-else-statement inside a double for-loop that goes through every cell of input 2D-Array.
	 * Then we're changing coordinates of X and Y axis in order to make the process of drawing lines easier.
	 */

	public static void draw(int[][] maze) {

		StdDraw.setCanvasSize(maze.length*100, maze.length*100);
		StdDraw.setXscale(0, maze.length);
        StdDraw.setYscale(-maze.length+1, +1);

		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze.length; j++){
				if(maze[i][j] == 0){
					StdDraw.setPenColor(StdDraw.GRAY);
					StdDraw.filledSquare(0.5 + j, 0.5 - i, 0.5);
				} else if(maze[i][j] == 1){
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledSquare(0.5 + j, 0.5 - i, 0.5);
				} else if(maze[i][j] == 2){
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.filledSquare(0.5 + j, 0.5 - i, 0.5);
				} else {
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.filledSquare(0.5 + j, 0.5 - i, 0.5);
				}
			}
		}

		StdDraw.setXscale(0, maze.length);
        StdDraw.setYscale(0, maze.length);

		for(int i = 0; i <= maze.length; i++){
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(maze.length , i, 0, i);
		}
		for(int j = 0; j <= maze.length; j++){
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(j, maze.length, j, 0);
		}

	}
}