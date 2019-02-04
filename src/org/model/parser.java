package org.model;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class parser {

    private String filename;
    private int measPerDay = 4;
    private int thirtyDayTotal = measPerDay * 30;
    private int annualTotal = measPerDay * 365;

     public parser(){
         filename = "data.txt";
     }

     public int[][] readThirtyDays(){
         int ret[][] = new int[2][thirtyDayTotal];
         int temp[][] = new int[1][1];

         try{
             Scanner in = new Scanner(new File(filename));

             for (int z = 0; z < thirtyDayTotal && in.hasNextInt(); z++){

             }
         }
         catch(FileNotFoundException e){
             System.out.println("File not found");
         }




         return ret;
     }
}
