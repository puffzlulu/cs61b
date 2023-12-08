package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {
    private class searchNode implements Comparable<searchNode>{
        WorldState worldState;
        int move;
        searchNode parent;

        searchNode(WorldState worldState,int move,searchNode parent){
            this.worldState=worldState;
            this.move=move;
            this.parent=parent;
        }

        @Override
        public int compareTo(searchNode searchNode) {
            return this.move+this.worldState.estimatedDistanceToGoal()- searchNode.move-searchNode.worldState.estimatedDistanceToGoal();
        }
    }

    private WorldState initial;
    private PriorityQueue<searchNode> neighbor;

    private int move;
    private ArrayList<WorldState> solution;

    /*Constructor which solves the puzzle, computing
      everything necessary for moves() and solution() to
      not have to solve the problem again. Solves the
      puzzle using the A* algorithm. Assumes a solution exists.*/
    public Solver(WorldState initial){
        this.initial=initial;
        this.neighbor=new PriorityQueue<>();
        neighbor.add(new searchNode(initial,0,null));
        this.solution=(ArrayList<WorldState>) solution();
    }

    /*Returns the minimum number of moves to solve the puzzle starting
      at the initial WorldState.*/
    public int moves(){
        return move;
    }

    private void getAnswer(searchNode goal){
        move=goal.move;
        solution=new ArrayList<>();
        searchNode p=goal;
        while (p!=null){
            solution.add(p.worldState);
            p=p.parent;
        }
    }

    /*Returns a sequence of WorldStates from the initial WorldState
     to the solution.*/
    public Iterable<WorldState> solution(){
        //List<WorldState> solute=new ArrayList<>();
        //solute.add(initial);
        searchNode X=neighbor.poll();
        if(X.worldState.isGoal()){
            getAnswer(X);
            return solution;
        }
        while (!X.worldState.isGoal()){
            for(WorldState temp:X.worldState.neighbors()){
                //update the new move
//                if(neighbor.contains(temp)){
//                }
                //avoid repeat
                if(X.parent==null||!temp.equals(X.parent)){
                    neighbor.add(new searchNode(temp,X.move+1,X));
                }
            }
            X=neighbor.poll();
            //solute.add(X.worldState);
            //move+=1;
        }
        getAnswer(X);
        return solution;
    }
}
