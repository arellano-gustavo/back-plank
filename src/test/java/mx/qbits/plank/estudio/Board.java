package mx.qbits.plank.estudio;

public class Board {
    private int width;
    private int height;
    private int contador;
    private int[][] board;
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public boolean isFull() {
        return (contador==this.width*this.height);
    }
    public boolean isDoneForQueenProblem() {
        return (contador==9);
    }
    public boolean isInside(int x, int y) {
        return (x>=0 && x<this.width && y>=0 && y<this.height);
    }
    public boolean isValid(int x, int y) {
        return (isInside(x, y) && board[x][y]==0);
    }
    public int data(int x, int y) {
        return isInside(x,y)?this.board[x][y]:-1;
    }
    public void remove(int x, int y) {
        if(isInside(x,y)) {
            contador --;
            this.board[x][y] = 0;
        }
    }
    public void put(int x, int y) {
        if(isValid(x,y)) {
            contador ++;
            this.board[x][y] = 1;
        }
    }
    public static String print(Board result) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<result.getHeight(); i ++) {
            for(int j=0; j<result.getWidth(); j++) {
                sb.append(result.data(i, j));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public String toString() {
        return print(this);
    }
    public void put2(int x, int y) {
        if(isValid(x,y)) {
            //pon sus diagonales
            for(int i=0;i<this.height; i++) {if(isInside(x+i,y+i))this.board[x+i][y+i] = 1;}
            for(int i=0;i<this.height; i++) {if(isInside(x-i,y-i))this.board[x-i][y-i] = 1;}
            for(int i=0;i<this.height; i++) {if(isInside(x+i,y-i))this.board[x+i][y-i] = 1;}
            for(int i=0;i<this.height; i++) {if(isInside(x-i,y-i))this.board[x-i][y+i] = 1;}
            // pon sus horizontales:
            for(int i=0;i<this.height; i++) this.board[x][i] = 1;
            // pon sus verticales:
            for(int i=0;i<this.width; i++)this.board[i][y] = 1;
        }
    }
}
