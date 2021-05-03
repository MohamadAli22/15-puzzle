/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astar_IDAstar;


import slidingtilepuzzle.*;
import java.util.Arrays;
/**
 *
 * @author mohamadAli
 */
public class State implements Cloneable {

    short [][] states;//short could be used instead of int
    short zeroRow;
    short zeroCol;
    short g;
    GameOperator previousOperator;

    public State(short[][] states, short zeroRow, short zeroCol) {
        this.states = states;
        this.zeroRow = zeroRow;
        this.zeroCol = zeroCol;
    }


    public short[][] getStates() {
        return states;
    }

    public void setStates(short[][] states) {
        this.states = states;
    }

    public short getZeroRow() {
        return zeroRow;
    }

    public void setZeroRow(short zeroRow) {
        this.zeroRow = zeroRow;
    }

    public short getZeroCol() {
        return zeroCol;
    }

    public void setZeroCol(short zeroCol) {
        this.zeroCol = zeroCol;
    }

    public short getG() {
        return g;
    }

    public void setG(short g) {
        this.g = g;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("zr:"+zeroRow+" zc:"+zeroCol+" g:"+g);
        sb.append("\n");
        sb.append(Arrays.toString(states[0]));
        sb.append("\n");
        sb.append(Arrays.toString(states[1]));
        sb.append("\n");
        sb.append(Arrays.toString(states[2]));
        sb.append("\n");
        sb.append(Arrays.toString(states[3]));
        sb.append("\n");
        sb.append(Arrays.toString(states[4]));
        sb.append("\n");
        return sb.toString();
    }
    
   public Object clone() throws CloneNotSupportedException
    {
        State state = (State) super.clone();
        return state;
    }   
}
