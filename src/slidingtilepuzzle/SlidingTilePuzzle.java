/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingtilepuzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamadAli
 */
public class SlidingTilePuzzle {

    static Puzzle puzzle;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        puzzle = new Puzzle();
        Utils u = new Utils();
        ArrayList<int[][]> testCases = u.readFile("C:\\Users\\mohamadAli\\Desktop\\korf100.txt");
        int counter = 1;
        for (int[][] testCase : testCases) {
            int[][] stateInfo = new int[4][4];
            System.out.printf("Problem %s of 100 \nSearching from:\n(4*4) ", counter);
            int zeroX = 0;
            int zeroY = 0;
            for (int i = 0; i < 16; i++) {
                System.out.print(testCase[i / 4][i % 4] + " ");
                stateInfo[i / 4][i % 4] = testCase[i / 4][i % 4];
                if (testCase[i / 4][i % 4] == 0) {
                    zeroX = i / 4;
                    zeroY = i % 4;
                }
            }
            System.out.println("");
            System.out.println("(4*4) 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
            
            long time = java.lang.System.currentTimeMillis();
            State state = new State(testCase, zeroX, zeroY);
            visitedStates = new HashSet();
            time = java.lang.System.currentTimeMillis() - time;
            
            State parent = solveIDAStar(state);
            
            int length = -1;
            while (parent != null) {
                parent = parent.parent;
                length++;
            }
            System.out.println("Path found!, length:"+length+", time:"+time);
            counter++;
        }
    }

    static State bestSolution;
    static HashSet visitedStates;
    static int newLimit;

    public static State solveIDAStar(State initialState) {
        int limit = puzzle.mdCalculator(initialState);
        newLimit = -1;
        bestSolution = null; // reset global to null
        State result = null;
        while (result == null) {
            System.out.println("starting iteration with bound: " + limit);
            result = limitedSearch(initialState, limit);
            limit = newLimit;
            newLimit = -1;
            visitedStates.clear();
        }
        return result;
    }

    public static State limitedSearch(State current, int limit) {
        try {
            State parent = (State) current.clone();
            for (GameOperator operator : puzzle.GetOperators(current)) {
                if(puzzle.isReverseOf(operator, parent.previousOperator)) continue;

//                System.out.println("ope:" + operator + "number of op:" + puzzle.GetOperators(current).size());
                State s = puzzle.applyOperator(operator, current);
                s.previousOperator = operator;
                s.setG(s.getG() + 1);

                if (puzzle.GoalTest(s)) {
                    s.setParent(parent);
                    return s;
                }
                if (!visitedStates.contains(s)) {
                    s.setParent(parent);
                    int currentCost = puzzle.mdCalculator(s) + s.getG();
                    if (currentCost <= limit) {
//                        System.out.println("1 cc" + currentCost);
                        visitedStates.add(s.toString());
                        State solution = limitedSearch(s, limit);
                    } else {
//                        System.out.println("2 cc:" + currentCost + " nl:" + newLimit + " l:" + limit);
                        if (currentCost < newLimit || newLimit == -1) {
                            newLimit = currentCost;
                        }
                    }
                }
                s.setG(s.getG() - 1);
                puzzle.UndoOperator(operator, s);
                s.previousOperator = parent.previousOperator;
            }
            return null;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SlidingTilePuzzle.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
