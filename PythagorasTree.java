import gdp.stdlib.*;

public class PythagorasTree {

/*
 * @author Gruppe 111
 * @version 1.0, 12/31/22
 */


    /*
    * drawSquare: 
    * The function prints a square of given coordinates. To construct a square the method draws 4 sides of this square using StdDraw.line() function.
    * @param coordinates  Array with coordinates of corners of a square. Format: {{x,y},...,{x,y}}
    */

    public static void drawSquare(double[][] coordinates){

        StdDraw.line(coordinates[0][0], coordinates[1][0], coordinates[0][1], coordinates[1][1]);
        StdDraw.line(coordinates[0][1], coordinates[1][1], coordinates[0][2], coordinates[1][2]);
        StdDraw.line(coordinates[0][2], coordinates[1][2], coordinates[0][3], coordinates[1][3]);
        StdDraw.line(coordinates[0][3], coordinates[1][3], coordinates[0][0], coordinates[1][0]);

        return;
    }

    /* 
    * rotate: 
    * The function calculates the coordinates of a rotated square by a given angle. The input of this function is the coordinates matrix of a square which will be rotated.
    * @param coordinates  Array with coordinates of corners of a square. Format: {{x,y},...,{x,y}}
    * @param angle  Angle alpha. In radians.
    * @return newCoordinates  Coordinates of a rotated square, given its coordinates. Format: {{x,y},...,{x,y}}
    */

    public static double[][] rotate(double[][] coordinates, double angle){

        double[][] newCoordinates = new double[2][4];

        for(int i = 0; i < 4; i++){

            newCoordinates[0][i] = (coordinates[0][i] * Math.cos(angle)) - (coordinates[1][i] * Math.sin(angle));
            newCoordinates[1][i] = (coordinates[0][i] * Math.sin(angle)) + (coordinates[1][i] * Math.cos(angle));

            }

        return newCoordinates;
    }

    /*
    * scale: 
    * The function calculates the coordinates of a scaled square. The scalar is equal to cosinus of a given angle.
    * @param coordinates  Array with coordinates of corners of a square. Format: {{x,y},...,{x,y}}
    * @param angle  Angle alpha. In radians.
    * @return newCoordinates  Coordinates of a scaled square, given its coordinates. Format: {{x,y},...,{x,y}}
    */

    public static double[][] scale(double[][] coordinates, double angle){

        double[][] newCoordinates = new double[2][4];

        for(int i = 0; i < 4; i++){
            newCoordinates[0][i] = coordinates[0][i] * Math.cos(angle);
            newCoordinates[1][i] = coordinates[1][i] * Math.cos(angle);
        }

        return newCoordinates;
    }

    /*
    * move:
    * The function calculates the coordinates of a scaled, rotated (move uses scale and rotate) and moved square, given the coordinates of a square.
    * A moved square is fixed to one of the corners of the input square and other points of the moved square are calculated using the sides of a rotated and scaled as vectors (square is only moved and not transformed)
    * @param coordinates  Array with coordinates of corners of a square. Format: {{x,y},...,{x,y}}
    * @param angle  Angle alpha. In radians.
    * @return newCoordinates  Coordinates of a square moved to the right position, given its coordinates. Format: {{x,y},...,{x,y}}
    */
        
    public static double[][] move(double[][] coordinates, double angle){

        double[][] newCoordinates = new double[2][4];
        double[][] transformedCoordinates = scale(rotate(coordinates, angle), angle);
        double[] verticalVector = new double[2];
        double[] horizontalVector = new double[2];

        verticalVector[0] = transformedCoordinates[0][3] - transformedCoordinates[0][0];
        verticalVector[1] = transformedCoordinates[1][3] - transformedCoordinates[1][0];

        horizontalVector[0] = transformedCoordinates[0][1] - transformedCoordinates[0][0];
        horizontalVector[1] = transformedCoordinates[1][1] - transformedCoordinates[1][0];

        if(angle > 0){

            newCoordinates[0][0] = coordinates[0][3];
            newCoordinates[1][0] = coordinates[1][3];
            
            newCoordinates[0][1] = newCoordinates[0][0] + horizontalVector[0];
            newCoordinates[1][1] = newCoordinates[1][0] + horizontalVector[1];

            newCoordinates[0][2] = newCoordinates[0][1] + verticalVector[0];
            newCoordinates[1][2] = newCoordinates[1][1] + verticalVector[1];

            newCoordinates[0][3] = newCoordinates[0][0] + verticalVector[0];
            newCoordinates[1][3] = newCoordinates[1][0] + verticalVector[1];

        } else if(angle < 0){

            newCoordinates[0][1] = coordinates[0][2];
            newCoordinates[1][1] = coordinates[1][2];
            
            newCoordinates[0][0] = newCoordinates[0][1] - horizontalVector[0];
            newCoordinates[1][0] = newCoordinates[1][1] - horizontalVector[1];

            newCoordinates[0][2] = newCoordinates[0][1] + verticalVector[0];
            newCoordinates[1][2] = newCoordinates[1][1] + verticalVector[1];
            
            newCoordinates[0][3] = newCoordinates[0][0] + verticalVector[0];
            newCoordinates[1][3] = newCoordinates[1][0] + verticalVector[1];

        }

        return newCoordinates;

    }

    /*
    * draw:
    * The function is recursive defined and it calls the drawSquare function (and then move) in order to draw a pythagoras tree of a given order n and based on a given square.
    * @param n  Number if iterations. Order of a pythagoras tree.
    * @param coordinates  Array with coordinates of corners of a square. Format: {{x,y},...,{x,y}}
    */

    public static void draw(int n, double[][] coordinates) {

        if (n < 0) return;

        drawSquare(coordinates);

        double alpha = Math.random() * Math.PI/6 + Math.PI/6;
        //double alpha = Math.random() * Math.PI/2;

        double[][] new_coordinates1 = move(coordinates, alpha);
        double[][] new_coordinates2 = move(coordinates, - (Math.PI/2 - alpha));

        draw(n-1, new_coordinates1);
        draw(n-1, new_coordinates2);
       
    }
    
    public static void main(String[] args) {

        /* 
        * Function draw is called in order to print out a pythagoras tree of a given order n (argument form command line) and based on a square with corners in points (-0.125, 0), (0.125, 0), (0.125, 0.25) and (-0.125, 0.25) (these can be changed if required).
        * The scale of X and Y axis is set to (-1, 1) and (0, 2), so that the pythagoras tree fits in the displayed window. 
        * @param args  Arguments form the command line. As array of strings.
        */

        int N = Integer.parseInt(args[0]);

        double[][] coordinates = {{-0.125,0.125,0.125,-0.125},{0,0,0.25,0.25}}; 

        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(0, 2);
        
        draw(N, coordinates);

    }
}