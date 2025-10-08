public class Board {
    public int size = 3; //définit la taille du plateau de jeu
    Cell[][] board;

    public Board() {
        this.board =  new Cell[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    };

    public Cell getCell(int i, int j) {
        return board[i][j];
    }

    public Cell setCell(int i, int j) {return board[i][j];}

    public void display() {
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j< board[i].length; j++) {
                Cell cell = board[i][j];
                System.out.print("| " + cell.getRepresentation() + " ");
            }
            System.out.println("|");
        }
    }
}
