public class Board{
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

    public Cell getCell(int col, int row) {
        return board[col][row];
    }


    public void display() {
        System.out.println("-------------");
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j< board[i].length; j++) {
                Cell cell = board[i][j];
                System.out.print("| " + cell.getRepresentation() + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    public boolean hasEmptyCell() {
        boolean hasEmpty = false;
        //vérifier si la grille contient encore une cellule vide
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getCell(i, j).isEmpty()) {
                    hasEmpty = true;
                    break;
                }
                if (hasEmpty) break;
            }
        }
        return hasEmpty;
    }
}
