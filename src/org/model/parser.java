package org.model;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class parser {

    private static String filename;
    private static int numOfTrans = 5;
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

     public static int[][] parseThirtyDayTotal(){
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

    public static int[][] parseAnnualTotal(){
        int ret[][] = new int[annualTotal][2];
        int hold;
        Scanner in = null;

        try{
            in = new Scanner(new File(filename));
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        for (int z = 0; z < annualTotal && in.hasNextInt(); z++){
            hold = in.nextInt();
            ret[z][0] = hold / 10000;
            ret[z][1] = hold % 10000;
            //System.out.printf("\n Module %d reads: %d", ret[z][0], ret[z][1]);
        }
        return ret;
    }

    public static int[][][] parseNodeDataAnnual(){
         int ret[][][] = new int[numOfTrans][2][annualTotal/numOfTrans];
         int arr[][] = parseAnnualTotal();

        boolean match = true;
        int count = 0;
        for (int z = 0; z < numOfTrans*3; z++){
            for (int x = 0; x < numOfTrans; x++){
                if (arr[z][0] == ret[x][0][0])
                    match = false;
            }
            if (match){
                ret[count][0][0] = arr[z][0];
                //System.out.println(ret[count][0][0]);
                count++;
            }
            match = true;
        }
        System.out.println();

         for (int x = 0; x < numOfTrans; x++){
             count = 0;
//             System.out.println();
//             System.out.println(ret[x][0][0]);
             for (int y = 0; y < annualTotal; y++){
                if (arr[y][0] == ret[x][0][0]){
                    ret[x][1][count] = arr[y][1];
//                    System.out.print(ret[x][1][count] + " ");
                    count++;
                }
             }
         }

         return ret;
    }

    public static int[][][] parseNodeDataThirtyDays(){
        int ret[][][] = new int[numOfTrans][2][thirtyDayTotal/numOfTrans];
        int arr[][] = parseAnnualTotal();

        boolean match = true;
        int count = 0;
        for (int z = 0; z < numOfTrans*3; z++){
            for (int x = 0; x < numOfTrans; x++){
                if (arr[z][0] == ret[x][0][0])
                    match = false;
            }
            if (match){
                ret[count][0][0] = arr[z][0];
                //System.out.println(ret[count][0][0]);
                count++;
            }
            match = true;
        }
        System.out.println();

        for (int x = 0; x < numOfTrans; x++){
            count = 0;
//            System.out.println();
//            System.out.println(ret[x][0][0]);
            for (int y = 0; y < thirtyDayTotal; y++){
                if (arr[y][0] == ret[x][0][0]){
                    ret[x][1][count] = arr[y][1];
//                    System.out.print(ret[x][1][count] + " ");
                    count++;
                }
            }
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
