import java.lang.reflect.Method;
import java.util.BitSet;

import gdp.stdlib.*;

public class Oktadoku {
  
  public enum Variante { normal, mitDiagonalen };

  public static int[][] integerMatrix = new int[8][8];

  public static char[][] charMatrix = new char[8][8];
  
  private Variante v;

  public Oktadoku(Variante var){
    
    

  }

  public void read(){
    
    String input;
    int count = 0;

    while(!StdIn.isEmpty()){
     
      input = StdIn.readLine();
      char[] inputArray = input.toCharArray();
      
      for(int i = 0; i < 8; i++){
        if(inputArray[i] == '.'){
          integerMatrix[count][i] = 0;
          charMatrix[count][i] = 0;
        } else {
          integerMatrix[count][i] = Integer.parseInt(String.valueOf(inputArray[i]));
          charMatrix[count][i] = inputArray[i];
        }
      }

      count++;
    }

    return;
  }

  public static void write(){

    for(int i = 0; i < 11; i++){
      for(int j = 0; j < 25; j++){
        if(i % 5 == 0){
          if(j % 6 == 0){
            System.out.print("+");
          } else {
            System.out.print("-");
          }
        } else {
          if(j % 2 == 1){
            System.out.print(" ");
          } else if(j % 6 == 0){
            System.out.print("|");
          } else {
            System.out.print(i < 5 ? charMatrix[i-1][j < 5 ? j/2-1 :
                                                     j < 11 ? j/2-1 :
                                                     j < 17 ? j/2-3 : j/2-4] :
                                     charMatrix[i-2][j < 5 ? j/2-1 :
                                                     j < 11 ? j/2-1 : 
                                                     j < 17 ? j/2-3 : j/2-4]);
          }
        }
      }
      System.out.println();
    }
    
    return;
  }

  public boolean check(){
    return true;
  }

  public void solve(){
    

    return;
  }

  public static void main(String[] args){

    String input;
    int count = 0;

    while(!StdIn.isEmpty()){
     
      input = StdIn.readLine();
      char[] inputArray = input.toCharArray();
      
      for(int i = 0; i < 8; i++){
        if(inputArray[i] == '.'){
          integerMatrix[count][i] = 0;
          charMatrix[count][i] = ' ';
        } else {
          integerMatrix[count][i] = Integer.parseInt(String.valueOf(inputArray[i]));
          charMatrix[count][i] = inputArray[i];
        }
      }

      count++;
    }


    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        System.out.print(integerMatrix[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        System.out.print(charMatrix[i][j] + " ");
      }
      System.out.println();
    }

    write();

  }
}

