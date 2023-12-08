package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private int[][] tiles;
    private final int BLANK=0;
    /**Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j*/
    public Board(int[][] tiles){
        this.tiles=new int[tiles.length+1][tiles[0].length+1];
        for(int i=0;i<tiles.length;i++){
            for(int j=0;j<tiles[0].length;j++){
                this.tiles[i][j]=tiles[i][j];
            }
        }
    }

    /**Returns value of tile at row i, column j (or 0 if blank)*/
    public int tileAt(int i, int j){
        if(i<0||i>=tiles.length||j<0||j>=tiles.length){
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    /**Returns the board size N*/
    public int size(){
        return tiles.length;
    }

    /**Returns the neighbors of the current board*/
    public Iterable<WorldState> neighbors(){
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /**Hamming estimate described below*/
    public int hamming(){
        int ham=0;
        for(int i=0;i<size();i++){
            for(int j=0;j<size();j++){
                if(tileAt(i,j)==0){
                    continue;
                }else {
                    if(tileAt(i,j)!=i*size()+j+1){
                        ham++;
                    }
                }
            }
        }
        return ham;
    }

    private int correctLocateRow(int num){
        return num/size();
    }

    private int correctLocateCol(int num){
        return num%size()-1;
    }

    /**Manhattan estimate described below*/
    public int manhattan(){
        int manh=0;
        for(int i=0;i<size();i++){
            for(int j=0;j<size();j++){
                if(tileAt(i,j)==BLANK){
                    continue;
                }else {
                    if(tileAt(i,j)!=i*size()+j+1){
                        manh+=Math.abs(i-correctLocateRow(tileAt(i,j)))+Math.abs(j-correctLocateCol(tileAt(i,j)));
                    }
                }
            }
        }
        return manh;
    }

    /**Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope.*/
    public int estimatedDistanceToGoal(){
        return manhattan();
    }

    /**Returns true if this board's tile values are the same
     position as y's*/
    public boolean equals(Object y){
        if(y==this){
            return true;
        } else if (y==null||this.getClass()!=y.getClass()) {
            return false;
        }
        Board jud=(Board) y;
        for(int i=0;i<size();i++){
            for(int j=0;j<size();j++){
                if(tileAt(i,j)!=jud.tileAt(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
