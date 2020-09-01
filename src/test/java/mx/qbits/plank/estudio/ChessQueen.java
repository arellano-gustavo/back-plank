package mx.qbits.plank.estudio;

public class ChessQueen {
    private boolean done = false;

    private int w = 8;
    private int h = 8;

    private int startX = 0;
    private int startY = 0;

    public static void main(String...argv) {
        new ChessQueen();
    }

    public ChessQueen() {
        Board origen = new Board(w,h);
        fill(startX, startY, origen);
        System.out.println("End");
    }

    private void fill(int x, int y, Board board) {
        board.put2(x, y);
        if(done || board.isDoneForQueenProblem()) {
            if(!done) {
                System.out.println("Ya acabé. Tablero lleno !!!");
                drawBoard(x, y);
            }
            done = true;
            return;
        }
        
        for(int i=1; i<board.getWidth(); i++) {
            for(int j=1; i<board.getHeight(); j++) {
                if(board.isValid(x+i,y+j)) fill(x+i,y+j,board);
            }
        }
        
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
