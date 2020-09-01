package mx.qbits.plank.estudio;

public class ChessHorse {
    private boolean done = false;

    private int w = 6;
    private int h = 6;

    private int startX = 0;
    private int startY = 0;

    public static void main(String...argv) {
        new ChessHorse();
    }

    public ChessHorse() {
        Board origen = new Board(w,h);
        fill(startX, startY, origen);
        System.out.println("End");
    }

    private void fill(int x, int y, Board board) {
        board.put(x, y);
        if(done || board.isFull()) {
            if(!done) {
                System.out.println("Ya acabé. Tablero lleno !!!");
                drawBoard(x, y);
            }
            done = true;
            return;
        }
        
        if(board.isValid(x+2,y+1)) fill(x+2,y+1,board);
        if(board.isValid(x+2,y-1)) fill(x+2,y-1,board);
        if(board.isValid(x+1,y+2)) fill(x+1,y+2,board);
        if(board.isValid(x+1,y-2)) fill(x+1,y-2,board);
        
        if(board.isValid(x-2,y+1)) fill(x-2,y+1,board);
        if(board.isValid(x-2,y-1)) fill(x-2,y-1,board);
        if(board.isValid(x-1,y+2)) fill(x-1,y+2,board);
        if(board.isValid(x-1,y-2)) fill(x-1,y-2,board);
        
        if(done) {
            drawBoard(x, y); // or... printMove(x, y);
        } else {
            board.remove(x, y);
        }
    }

    private void printMove(int x, int y) {
        System.out.println("("+(y+1)+","+(x+1)+")");
    }

    Board result = new Board(w, h);
    public void drawBoard(int x, int y) {
        printMove(x, y);
        result.put(x, y);
        System.out.println(Board.print(result));
    }

}
