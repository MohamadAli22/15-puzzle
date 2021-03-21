/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingtilepuzzle;


import java.util.Arrays;
/**
 *
 * @author mohamadAli
 */
public class State implements Cloneable {

    int [][] states;
    int zeroRow;
    int zeroCol;
    int g;
    State parent;
    GameOperator previousOperator;

    public State(int[][] states, int zeroRow, int zeroCol) {
        this.states = states;
        this.zeroRow = zeroRow;
        this.zeroCol = zeroCol;
    }

    public int[][] getStates() {
        return states;
    }

    public void setStates(int[][] states) {
        this.states = states;
    }

    public int getZeroRow() {
        return zeroRow;
    }

    public void setZeroRow(int zeroRow) {
        this.zeroRow = zeroRow;
    }

    public int getZeroCol() {
        return zeroCol;
    }

    public void setZeroCol(int zeroCol) {
        this.zeroCol = zeroCol;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
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
        return sb.toString();
    }
    
   public Object clone() throws CloneNotSupportedException
    {
        State state = (State) super.clone();
        return state;
    }
}
