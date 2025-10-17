package view;

import model.Cell;

public class View {

    public void display(Cell[][] board) {
        System.out.println("-------------");
        // Parcourir les LIGNES (deuxième indice)
        for (int i = 0; i < board[0].length; i++) {
            // Parcourir les COLONNES (premier indice)
            for (int j = 0; j < board.length; j++) {
                Cell cell = board[j][i]; // Inversion des indices pour afficher correctement
                System.out.print("| " + cell.getRepresentation() + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

}
