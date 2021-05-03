/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astar_IDAstar;

import slidingtilepuzzle.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mohamadAli
 */
public class Utils {
    
    ArrayList <short[][]> array ;

    public ArrayList readFile(String address) {
        array = new ArrayList<>();
        try {
            File file = new File(address);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                short[][] states = new short[5][5];
                String data = myReader.nextLine();
                String[] splited = data.split("\\s+");
                for (int i=0 ; i<splited.length ; i++){
                    if (i>0 && i<26){
                        states[(i-1)/5][(i-1)%5] = (short) Integer.parseInt(splited[i]);
                    }
                }
                array.add(states);
            }
            myReader.close();
            return array;
        } catch (FileNotFoundException ex) {
            System.out.println("exeption "+ ex.getMessage());
        }
        return null;
    }
}
