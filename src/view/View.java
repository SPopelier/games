package view;

import model.Cell;

public class View {

        public void display(Cell[][] board) {
        System.out.println("-------------");
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j< board.length; j++) {
                Cell cell = board[i][j];
                System.out.print("| " + cell.getRepresentation() + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

}
