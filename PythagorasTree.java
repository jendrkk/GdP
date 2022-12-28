import gdp.stdlib.*;

public class PythagorasTree {
    public static void drawSquare(double[][] coordinates){

        StdDraw.line(coordinates[0][0], coordinates[1][0], coordinates[0][3], coordinates[1][3]);
        StdDraw.line(coordinates[0][1], coordinates[1][1], coordinates[0][2], coordinates[1][2]);
        StdDraw.line(coordinates[0][0], coordinates[1][0], coordinates[0][1], coordinates[1][1]);
        StdDraw.line(coordinates[0][3], coordinates[1][3], coordinates[0][2], coordinates[1][2]);

    }

    public static double[][] rotate(double[][] coordinates, double rad){
        double[][] new_coordinates = new double[2][4];    
        
        for(int i = 0 ; i < 4; i++){
                new_coordinates[0][i] = coordinates[0][i] * Math.cos(rad) - coordinates[1][i] * Math.sin(rad);
                new_coordinates[1][i] = coordinates[0][i] * Math.sin(rad) + coordinates[1][i] * Math.cos(rad);
        }
        return new_coordinates;
    }

    public static double[][] scale(double[][] coordinates, double s){
        double[][] new_coordinates = new double[2][4];    
        
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 4; j++){
                new_coordinates[i][j] = coordinates[i][j] * s;
            }
        }

        return new_coordinates;
    }
    
    public static void draw(int n, double[][] coordinates) {
        if (n == 0){
            drawSquare(coordinates);
            return;
        }

        double angle = Math.PI/6 + Math.random() * Math.PI/6;

        drawSquare(coordinates);

        draw(n-1, rotate(coordinates, angle));    // upper left  H-tree    // upper right H-tree\
       
    }
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        double[][] coordinates = {{0.375,0.625,0.625,0.375},{0,0,0.25,0.25}}; 

        draw(N, coordinates);
    }
}