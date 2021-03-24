/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingtilepuzzle;

import java.util.ArrayList;

enum GameOperator {
    UP,
    DOWN,
    LEFT,
    RIGHT
};

/**
 *
 * @author mohamadAli
 */
public class Puzzle {

    public State applyOperator(GameOperator operator, State state) {
        switch (operator) {
            case UP:
                moveUp(state);
                break;
            case DOWN:
                moveDown(state);
                break;
            case LEFT:
                moveLeft(state);
                break;
            case RIGHT:
                moveRight(state);
                break;
        }
        //state.setG(state.getG()+1);
        return state;
    }

    public void UndoOperator(GameOperator operator, State state) {
        switch (operator) {
            case UP:
                moveDown(state);
                break;
            case DOWN:
                moveUp(state);
                break;
            case LEFT:
                moveRight(state);
                break;
            case RIGHT:
                moveLeft(state);
                break;
        }
    }

    public ArrayList<GameOperator> GetOperators(State state) {
        ArrayList ar = new ArrayList();
        if(state.getZeroCol()>0)
             ar.add(GameOperator.LEFT);
        if(state.getZeroRow()>0)
             ar.add(GameOperator.UP);
        if(state.getZeroCol()<3)
             ar.add(GameOperator.RIGHT);
        if(state.getZeroRow()<3)
             ar.add(GameOperator.DOWN);
        return ar;
    }

    public boolean GoalTest(State state) {
        if(state.getZeroCol()!=0 || state.getZeroRow()!= 0)
            return false;
        if(state.getStates()[0][1] != 1)
            return false;
        if(state.getStates()[0][2] != 2)
            return false;
        if(state.getStates()[0][3] != 3)
            return false;
        if(state.getStates()[1][0] != 4)
            return false;
        if(state.getStates()[1][1] != 5)
            return false;
        if(state.getStates()[1][2] != 6)
            return false;
        if(state.getStates()[1][3] != 7)
            return false;
        if(state.getStates()[2][0] != 8)
            return false;
        if(state.getStates()[2][1] != 9)
            return false;
        if(state.getStates()[2][2] != 10)
            return false;
        if(state.getStates()[2][3] != 11)
            return false;
        if(state.getStates()[3][0] != 12)
            return false;
        if(state.getStates()[3][1] != 13)
            return false;
        if(state.getStates()[3][2] != 14)
            return false;
        if(state.getStates()[3][3] != 15)
            return false;
        
        return true;
    }

    public void moveUp(State state) {
        short upperTile = state.getStates()[state.getZeroRow()-1][state.getZeroCol()];
        state.getStates()[state.getZeroRow()-1][state.getZeroCol()] = 0;
        state.getStates()[state.getZeroRow()][state.getZeroCol()] = upperTile;
        
        state.setZeroRow((short) (state.getZeroRow()-1));
        state.setZeroCol(state.getZeroCol());
    }

    public void moveDown(State state) {
        short bottomTile = state.getStates()[state.getZeroRow()+1][state.getZeroCol()];
        state.getStates()[state.getZeroRow()+1][state.getZeroCol()] = 0;
        state.getStates()[state.getZeroRow()][state.getZeroCol()] = bottomTile;
        
        state.setZeroRow((short) (state.getZeroRow()+1));
        state.setZeroCol(state.getZeroCol());
    }

    public void moveLeft(State state) {
        short leftTile = state.getStates()[state.getZeroRow()][state.getZeroCol()-1];
        state.getStates()[state.getZeroRow()][state.getZeroCol()-1] = 0;
        state.getStates()[state.getZeroRow()][state.getZeroCol()] = leftTile;
        
        state.setZeroRow(state.getZeroRow());
        state.setZeroCol((short) (state.getZeroCol()-1));
    }

    public void moveRight(State state) {
        short rightTile = state.getStates()[state.getZeroRow()][state.getZeroCol()+1];
        state.getStates()[state.getZeroRow()][state.getZeroCol()+1] = 0;
        state.getStates()[state.getZeroRow()][state.getZeroCol()] = rightTile;
        
        state.setZeroRow(state.getZeroRow());
        state.setZeroCol((short) (state.getZeroCol()+1));
    }
    
    public short mdCalculator(State state){
        short distance = 0;
        for(short i=0; i<16; i++){
            short value = state.getStates()[i/4][i%4];
            if(value==0)continue;
            distance += Math.abs((value/4)-(i/4)) + Math.abs((value%4)-(i%4));
        }
        return distance;
    }
    
    public boolean isReverseOf(GameOperator operator1, GameOperator operator2){
        if(operator1 == GameOperator.DOWN && operator2 == GameOperator.UP) return true;
        else if(operator1 == GameOperator.UP && operator2 == GameOperator.DOWN) return true;
        else if(operator1 == GameOperator.LEFT && operator2 == GameOperator.RIGHT) return true;
        else if(operator1 == GameOperator.RIGHT && operator2 == GameOperator.LEFT) return true;
        return false;
    }
}
