/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Astar_IDAstar;

import java.util.*;
/**
 *
 * @author mohamadAli
 */
public class AStar {
   
    private static int DEFAULT_HV_COST = 1;
    private int hvCost;
    private State[][] searchArea;
    private PriorityQueue<State> openList;
    private Set<State> closedSet;
    private State initialNode;
    private State finalNode;
    private Puzzle puzzle;

    public AStar(int rows, int cols, State initialNode, State finalNode, int hvCost) {
        this.hvCost = hvCost;
        setInitialNode(initialNode);
        setFinalNode(finalNode);
        this.puzzle = new Puzzle();
        this.searchArea = new State[rows][cols];
        this.openList = new PriorityQueue<State>(new Comparator<State>() {
            @Override
            public int compare(State node0, State node1) {
                return Integer.compare(node0.g+puzzle.mdCalculator(node0), node1.g+puzzle.mdCalculator(node1));
            }
        });
        setNodes();
        this.closedSet = new HashSet<>();
    }

    public AStar(int rows, int cols, State initialNode, State finalNode) {
        this(rows, cols, initialNode, finalNode, DEFAULT_HV_COST);
    }

    private void setNodes() {
        /*
        * todo read and init nodes from a sample testcase of 5*5 puzzle 
        */
//        for (int i = 0; i < searchArea.length; i++) {
//            for (int j = 0; j < searchArea[0].length; j++) {
//                Node node = new Node(i, j);
//                node.calculateHeuristic(getFinalNode());
//                this.searchArea[i][j] = node;
//            }
//        }
    }

    public List<State> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            State currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<State>();
    }

    private List<State> getPath(State currentNode) {
        //todo implement this
        
//        List<State> path = new ArrayList<State>();
//        path.add(currentNode);
//        State parent;
//        while ((parent = currentNode.getParent()) != null) {
//            path.add(0, parent);
//            currentNode = parent;
//        }
//        return path;
    return null;
    }

    private void addAdjacentNodes(State currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    private void addAdjacentLowerRow(State currentNode) {
        short zRow = currentNode.zeroCol;
        short zCol = currentNode.zeroRow;
        int lowerRow = zRow + 1;
        if (lowerRow < getSearchArea().length) {
            checkNode(currentNode, zCol, lowerRow, getHvCost());
        }
    }

    private void addAdjacentMiddleRow(State currentNode) {
        short zRow = currentNode.zeroCol;
        short zCol = currentNode.zeroRow;
        int middleRow = zRow;
        if (zCol - 1 >= 0) {
            checkNode(currentNode, zCol - 1, middleRow, getHvCost());
        }
        if (zCol + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, zCol + 1, middleRow, getHvCost());
        }
    }

    private void addAdjacentUpperRow(State currentNode) {
        short zRow = currentNode.zeroCol;
        short zCol = currentNode.zeroRow;
        int upperRow = zRow - 1;
        if (upperRow >= 0) {
            checkNode(currentNode, zCol, upperRow, getHvCost());
        }
    }

    private void checkNode(State currentNode, int col, int row, int cost) {
        State adjacentNode = getSearchArea()[row][col];
        if (!getClosedSet().contains(adjacentNode)) {
            if (!getOpenList().contains(adjacentNode)) {
//                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
            } else {
//                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
//                if (changed) {
                    // Remove and Add the changed node, so that the PriorityQueue can sort again its
                    // contents with the modified "finalCost" value of the modified node
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
//                }
            }
        }
    }

    private boolean isFinalNode(State currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<State> openList) {
        return openList.size() == 0;
    }

    public State getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(State initialNode) {
        this.initialNode = initialNode;
    }

    public State getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(State finalNode) {
        this.finalNode = finalNode;
    }

    public State[][] getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(State[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<State> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<State> openList) {
        this.openList = openList;
    }

    public Set<State> getClosedSet() {
        return closedSet;
    }

    public void setClosedSet(Set<State> closedSet) {
        this.closedSet = closedSet;
    }

    public int getHvCost() {
        return hvCost;
    }

    public void setHvCost(int hvCost) {
        this.hvCost = hvCost;
    }
    
}
