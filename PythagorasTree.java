import java.beans.Transient;

import gdp.stdlib.*;

public class PythagorasTree {
    public static void drawSquare(double[][] coordinates){

        StdDraw.line(coordinates[0][0], coordinates[1][0], coordinates[0][1], coordinates[1][1]);
        StdDraw.line(coordinates[0][1], coordinates[1][1], coordinates[0][2], coordinates[1][2]);
        StdDraw.line(coordinates[0][2], coordinates[1][2], coordinates[0][3], coordinates[1][3]);
        StdDraw.line(coordinates[0][3], coordinates[1][3], coordinates[0][0], coordinates[1][0]);

        return;
    }

    public static double[][] rotate(double[][] coordinates, double angle){
        double[][] newCoordinates = new double[2][4];

        for(int i = 0; i < 4; i++){
            if(angle > 0){
                newCoordinates[0][i] = (coordinates[0][i] * Math.cos(angle)) - (coordinates[1][i] * Math.sin(angle));
                newCoordinates[1][i] = (coordinates[0][i] * Math.sin(angle)) + (coordinates[1][i] * Math.cos(angle));
            } else if (angle < 0){
                double new_angle = Math.PI/2 + angle;

                newCoordinates[0][i] = (coordinates[0][i] * Math.sin(new_angle)) - (coordinates[1][i] * Math.cos(new_angle));
                newCoordinates[1][i] = (coordinates[0][i] * Math.cos(new_angle)) + (coordinates[1][i] * Math.sin(new_angle));
            }
        }

        return newCoordinates;
    }

    public static double[][] scale(double[][] coordinates, double angle){
        double[][] newCoordinates = new double[2][4];

        for(int i = 0; i < 4; i++){
            if(angle > 0){
                newCoordinates[0][i] = coordinates[0][i] * Math.cos(angle);
                newCoordinates[1][i] = coordinates[1][i] * Math.cos(angle);
            } else if(angle < 0){
                double new_angle = Math.PI/2 + angle;


                newCoordinates[0][i] = coordinates[0][i] * Math.cos(new_angle);
                newCoordinates[1][i] = coordinates[1][i] * Math.cos(new_angle);
            }
        }

        return newCoordinates;
    }

    public static double[][] fasten(double[][] coordinates, double angle){
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
            
            newCoordinates[0][0] = newCoordinates[0][1] + horizontalVector[0];
            newCoordinates[1][0] = newCoordinates[1][1] + horizontalVector[1];

            newCoordinates[0][2] = newCoordinates[0][1] + verticalVector[0];
            newCoordinates[1][2] = newCoordinates[1][1] + verticalVector[1];
            
            newCoordinates[0][3] = newCoordinates[0][0] + verticalVector[0];
            newCoordinates[1][3] = newCoordinates[1][0] + verticalVector[1];

        }

        return newCoordinates;
    }

    public static void draw(int n, double[][] coordinates) {
        if (n < 0) return;

        drawSquare(coordinates);

        double angle; 
        do{
        angle = Math.PI/2 * Math.random();
        } while(angle <= Math.PI/6 || angle > Math.PI/3);

        double[][] new_coordinates1 = fasten(coordinates, angle);
        double[][] new_coordinates2 = fasten(coordinates, - angle);

        draw(n-1, new_coordinates1);
        draw(n-1, new_coordinates2);
       
    }
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdDraw.setXscale(-0.5, 0.5);
        StdDraw.setYscale(0, 1);
        double[][] coordinates = {{-0.125,0.125,0.125,-0.125},{0,0,0.25,0.25}}; 
        draw(N, coordinates);
    }
}