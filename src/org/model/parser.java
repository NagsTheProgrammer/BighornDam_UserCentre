package org.model;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class parser {

    private static String filename;
    private static int numOfTrans = 4;
    private static int measPerDay = 4;
    private static int thirtyDayTotal = measPerDay * numOfTrans * 30;
    private static int annualTotal = measPerDay *numOfTrans* 365;

     public parser(){
         this.filename = "data.txt";
     }

     public parser(String file){
         this.filename = file;
     }

     public void setFilename(String file){
         this.filename = file;
     }

     public int getNumOfTrans(){
         return numOfTrans;
     }

     public int getMeasPerDay(){
         return measPerDay;
     }

     public static int[][] readThirtyDayTotal(){
         int ret[][] = new int[thirtyDayTotal][2];
         int hold;

         Scanner in = null;

         try{
             in = new Scanner(new File(filename));
         }
         catch(FileNotFoundException e){
             System.out.println("File not found");
         }

         for (int z = 0; z < thirtyDayTotal && in.hasNextInt(); z++){
             hold = in.nextInt();
             ret[z][0] = hold / 10000;
             ret[z][1] = hold % 10000;
             //System.out.printf("\n Module %d reads: %d", ret[z][0], ret[z][1]);
         }

         return ret;
     }

    public static int[][] readAnnualTotal(){
        int ret[][] = new int[2][annualTotal];
        int hold;

        try{
            Scanner in = new Scanner(new File(filename));

            for (int z = 0; z < annualTotal && in.hasNextInt(); z++){
                hold = in.nextInt();
                ret[z][0] = hold / 10000;
                ret[z][1] = hold % 10000;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        return ret;
    }

    public static void printArray(int[][] arr){
         System.out.println("Printing array values.");
         for (int z = 0; z < arr.length; z++){
             System.out.printf("Module %d reads: %d\n", arr[z][0], arr[z][1]);
         }
    }
}
