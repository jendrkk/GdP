import gdp.stdlib.StdDraw;

public class MazeTester {
	
	static final int SHOWTIME=1000;

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		switch (n) {
			case 1:
				test1();
				break;
			case 2:
				test2();
				break;
			case 3:
				test3();
				break;
			case 4:
				test4();
				break;
			case 5:
				test5();
				break;
			case 6:
				test6();
				break;
			case 7:
				test7();
				break;
			default:
				System.out.println("Testfall nicht bekannt.");
				System.out.println("1 <= n <= 7");
				break;
		}
	}
	
	public static void test1() { 
		runTest(true, new int[][] {
			{ 0, 0, 1, 1, 1, 1, 1, 2 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 0 },
			{ 1, 1, 1, 1, 1, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 3, 1, 1, 0, 0, 0, 0, 0 }
		});
	}

	public static void test2() {
		runTest(true, new int[][] {
			{ 0, 0, 1, 1, 1, 1, 1, 2 },
			{ 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 0 },
			{ 1, 1, 1, 1, 1, 1, 0, 0 },
			{ 1, 0, 1, 0, 1, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 1, 0, 0 },
			{ 3, 1, 1, 0, 0, 0, 0, 0 }
		});
	}

	public static void test3() {
		runTest(true, new int[][] {
			{ 0, 0, 1, 1, 1, 1, 1, 2 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 0 },
			{ 1, 1, 1, 1, 1, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 0, 0 },
			{ 3, 1, 0, 0, 0, 0, 0, 0 }
		});
	}

	public static void test4() {
		runTest(true, new int[][] {
			{ 0, 0, 1, 1, 1, 1, 1, 2 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 1, 1, 1, 0 },
			{ 1, 1, 1, 0, 1, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 3, 1, 1, 0, 0, 0, 0, 0 }
		});
	}

	public static void test5() {
		runTest(true, new int[][] {
			{ 0, 0, 1, 1, 1, 1, 1, 2 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 1, 1, 1, 0 },
			{ 1, 1, 1, 1, 1, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 1, 1, 0, 1, 0, 0, 0 },
			{ 3, 1, 0, 0, 0, 0, 0, 0 }
		});
	}

	public static void test6() {
		runTest(false, new int[][] {
			{ 0, 0, 1, 1, 1, 1, 1, 2 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 1, 1, 1, 0 },
			{ 1, 1, 1, 1, 1, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 3, 1, 0, 0, 0, 0, 0, 0 }
		});
	}
	public static void test7() {
		runTest(false, new int[][] {
			{ 0, 0, 1, 1, 1, 1, 1, 2 },
			{ 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 1, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 0 },
			{ 1, 1, 0, 0, 1, 0, 1, 0 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 },
			{ 0, 0, 1, 0, 1, 0, 0, 0 },
			{ 3, 1, 1, 1, 1, 0, 0, 0 }
		});
	}
	/**
	 * Generalisierte Testung ...
	 * @param solvable ist das Labirint lösbar?
	 * @param maze Labirinth
	 */
	static void runTest(boolean solvable, int[][] maze) {
		// malen lassen
		MazeSolver.draw(maze);   
		StdDraw.show(SHOWTIME);
		//lösen lassen
		boolean result = MazeSolver.solve(maze, 0, maze.length-1); 
		// wenn Lösung dann erneut malen lassen
		if(result) {                 
			MazeSolver.draw(maze);
			StdDraw.show(SHOWTIME);			
		}
		// Auswertung
		System.out.println((result == solvable) ? "Ihre Lösung ist korrekt." : "Ihre Lösung ist falsch.");	
	}

	static void printMaze(int[][] maze) {
		System.out.println();
		for (int[] row : maze) {
			for (int field : row) {
				System.out.print(field + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}