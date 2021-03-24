/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingtilepuzzle;

import java.util.ArrayList;
import java.util.HashSet;

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
        ArrayList<short[][]> testCases = u.readFile("C:\\Users\\mohamadAli\\Desktop\\korf100.txt");
        int counter = 1;
        for (short[][] testCase : testCases) {
            int[][] stateInfo = new int[4][4];
            System.out.printf("Problem %s of 100 \nSearching from:\n(4*4) ", counter);
            short zeroX = 0;
            short zeroY = 0;
            for (short i = 0; i < 16; i++) {
                System.out.print(testCase[i / 4][i % 4] + " ");
                stateInfo[i / 4][i % 4] = testCase[i / 4][i % 4];
                if (testCase[i / 4][i % 4] == 0) {
                    zeroX = (short) (i / 4);
                    zeroY = (short) (i % 4);
                }
            }
            System.out.println("");
            System.out.println("(4*4) 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
            
            long time = java.lang.System.currentTimeMillis();
            State state = new State(testCase, zeroX, zeroY);
            visitedStates = new HashSet();
            solution = null;
            State solution = solveIDAStar(state);
            time = java.lang.System.currentTimeMillis() - time;
            
            System.out.println("Path found!, length:" + solution.getG() + ", time:" + time + " (ms)");
            
            counter++;
        }
    }
    
    static HashSet visitedStates;
    static int newLimit;
    
    public static State solveIDAStar(State initialState) {
        int limit = puzzle.mdCalculator(initialState);
        newLimit = -1;
        State result = null;
        while (result == null) {
            System.out.println("starting iteration with bound: " + limit);
            result = limitedSearch(initialState, limit, null);
            limit = newLimit;
            newLimit = -1;
            visitedStates.clear();
        }
        return result;
    }
    
    static State solution = null;
    
    public static State limitedSearch(State current, int limit, GameOperator preOperator) {
        if (puzzle.GoalTest(current)) {
                    System.out.println("here I found solution");
                    current.previousOperator = preOperator;
                    return current;
                }
//        System.out.println("---------------------------------------------------------------");
//        System.out.println("cu" + current.toString() + "\n h:" + puzzle.mdCalculator(current));
//        System.out.println("---------------------------------------------------------------");
        State parent = null;
        try {
            parent = (State) current.clone();
            for (GameOperator operator : puzzle.GetOperators(current)) {
                if (puzzle.isReverseOf(operator, parent.previousOperator)) {
                    continue;
                }if (current.g > 70){
                    break;
                }
                
//              System.out.println("ope:" + operator + "number of op:" + puzzle.GetOperators(current).size());
                State s = puzzle.applyOperator(operator, current);
                s.previousOperator = operator;
                s.setG((short) (s.getG() + 1));
                
                if (!visitedStates.contains(s)) {
                    int currentCost = puzzle.mdCalculator(s) + s.getG();
                    if (currentCost <= limit) {
//                        System.out.println("1 cc" + currentCost);
                        visitedStates.add(s.toString());
                        solution = limitedSearch(s, limit, operator);
                        if (solution != null) {
                            return solution;
                        }
                    } else {
//                        System.out.println("2 cc:" + currentCost + " nl:" + newLimit + " l:" + limit);
                        if (currentCost < newLimit || newLimit == -1) {
                            newLimit = currentCost;
                        }
                    }
                }
                s.setG((short) (s.getG() - 1));
                puzzle.UndoOperator(operator, s);
                s.previousOperator = parent.previousOperator;
            }
            if (solution != null) {
                return solution;
            }
            return null;
        } catch (Exception ex) {
            System.out.println(" exxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx " + ex.toString());
            System.out.println("cu" + current.toString());
            System.out.println("pr" + parent.toString());
            if (solution != null) {
                return solution;
            }
            return null;
        }
    }
    
}
